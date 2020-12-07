package ru.kinopoisk.server.services.gamesDbService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kinopoisk.server.parsers.GamesParserService;
import ru.kinopoisk.server.persistence.domain.Developer;
import ru.kinopoisk.server.persistence.domain.Game;
import ru.kinopoisk.server.persistence.domain.Genre;
import ru.kinopoisk.server.persistence.domain.Platform;
import ru.kinopoisk.server.persistence.dto.GameDto;
import ru.kinopoisk.server.services.DeveloperService;
import ru.kinopoisk.server.services.GameService;
import ru.kinopoisk.server.services.GenreService;
import ru.kinopoisk.server.services.PlatformService;
import ru.kinopoisk.server.services.mappers.GameMapper;
import ru.kinopoisk.server.utils.Constraints;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class GamesService implements IGamesService {

    @Autowired
    GameService gameService;

    @Autowired
    DeveloperService developerService;

    @Autowired
    PlatformService platformService;

    @Autowired
    GenreService genreService;

    @Autowired
    GamesParserService gamesParserService;

    @Autowired
    GameMapper gameMapper;

    @Override
    public void savePlatformToDb() {
        for (String platform : Constraints.platforms) {
            platformService.save(new Platform(platform));
        }
    }

    @Override
    public void saveDeveloperToDb(List<String> developers) {
        if (developerService.getAll().isEmpty()) {
            for (String developer : developers) {
                developerService.save(new Developer(developer));
            }
        } else {
            for (String developer : developers) {
                if (developerService.findByName(developer) == null) {
                    developerService.save(new Developer(developer));
                }
            }
        }

    }

    @Override
    public void saveGenreToDb() {
        for (String genre : Constraints.genres) {
            genreService.save(new Genre(genre));
        }
    }

    @Override
    public void saveGamesToDb(int firstPageNumber, int lastPageNumber) throws ExecutionException, InterruptedException {
        List<GameDto> games = gamesParserService.getAllGamesFromPages(firstPageNumber, lastPageNumber);

        if(platformService.getAll().isEmpty() && genreService.getAll().isEmpty()){
            saveGenreToDb();
            savePlatformToDb();
        }

            List<Platform> platformsFromDb = platformService.getAll();
            List<Genre> genresFromDb = genreService.getAll();
            for (GameDto gameDto : games) {
                if (gameService.findByName(gameDto.getOriginalName()) == null) {
                    Game game = gameMapper.mapToEntity(gameDto);
                    saveDeveloperToDb(gameDto.getDevelopers());
                    List<Developer> developersFromDb = developerService.getAll();
                    for (String platform : gameDto.getPlatforms()) {
                        Platform platformFromDb = platformsFromDb.stream().filter(x -> x.getName().equals(platform)).findFirst().orElse(null);
                        game.addPlatform(platformFromDb);
                    }
                    for (String developer : gameDto.getDevelopers()) {
                        Developer developerFromDb = developersFromDb.stream().filter(x -> x.getName().equals(developer)).findFirst().orElse(null);
                        game.addDeveloper(developerFromDb);
                    }

                    for (String genre : gameDto.getGenres()) {
                        Genre genreFromDb = genresFromDb.stream().filter(x -> x.getName().equals(genre)).findFirst().orElse(null);
                        game.addGenre(genreFromDb);
                    }
                    gameService.save(game);
                }
            }
        System.out.println("Все добавлено");

    }
}
