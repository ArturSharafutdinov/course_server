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

import java.util.*;
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

    Set<String> developersLikeDb = new HashSet<>();

    @Override
    public void savePlatformToDb() {
        for (String platform : Constraints.platforms) {
            platformService.save(new Platform(platform));
        }
    }

    @Override
    public void saveDeveloperToDb(List<String> developers) {
            for (String developer : developers) {
                if(developerService.findByName(developer)==null)
                {
                    developerService.save(new Developer(developer));
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

       // System.out.println(games.size());

        if(platformService.getAll().isEmpty() && genreService.getAll().isEmpty()) {
            saveGenreToDb();
            savePlatformToDb();
        }


        //Сразу с бд подгружаем, чтобы не отправлять запросы findBy
            List<Platform> platformsFromDb = platformService.getAll();
            List<Genre> genresFromDb = genreService.getAll();

            // Через итератор, чтобы опять не словить OutOfMemory : собрали инфу -> сохранили -> удалили
        ListIterator<GameDto> gamesIterator = games.listIterator();
           while(gamesIterator.hasNext()) {
               GameDto gameDto = gamesIterator.next();
  if(gameService.findByName(gameDto.getOriginalName())==null){
      Game game = gameMapper.mapToEntity(gameDto);
      saveDeveloperToDb(gameDto.getDevelopers());
      for (String platform : gameDto.getPlatforms()) {
          Platform platformFromDb = platformsFromDb.stream().filter(x -> x.getName().equals(platform)).findFirst().orElse(null);
          game.addPlatform(platformFromDb);
      }
      for (String developer : gameDto.getDevelopers()) {
          Developer developerFromDb = developerService.findByName(developer);
          game.addDeveloper(developerFromDb);
      }

      for (String genre : gameDto.getGenres()) {
          Genre genreFromDb = genresFromDb.stream().filter(x -> x.getName().equals(genre)).findFirst().orElse(null);
          game.addGenre(genreFromDb);
      }
      gameService.save(game);
  }
                    gamesIterator.remove();
            }

    }
}
