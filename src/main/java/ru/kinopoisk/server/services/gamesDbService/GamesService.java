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
public class GamesService implements IGamesService{

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
        for(String platform : Constraints.platforms){
            platformService.save(new Platform(platform));
        }
    }

    @Override
    public void saveDeveloperToDb(List<String> developers) {
        if(developerService.getAll().isEmpty()){
            for(String developer : developers){
                developerService.save(new Developer(developer));
            }
        }
        else
        {
            for(String developer : developers){
                if(developerService.findByName(developer)==null){
                    developerService.save(new Developer(developer));
                }
            }
        }

    }

    @Override
    public void saveGenreToDb() {
for(String genre : Constraints.genres){
    genreService.save(new Genre(genre));
}
    }

    @Override
    public void saveGamesToDb(int firstPageNumber, int lastPageNumber) throws ExecutionException, InterruptedException {
        List<GameDto> games = gamesParserService.getAllGamesFromPages(firstPageNumber,lastPageNumber);
        List<Game> gamesFromDb = gameService.getAll();

        if(!gamesFromDb.isEmpty()){
            for(GameDto gameDto : games){
                if(gameService.findByName(gameDto.getOriginalName())==null){
                  //  saveDeveloperToDb(gameDto.getDevelopers());
                    Game game = gameMapper.mapToEntity(gameDto);

                }
            }
        }
        else
        {
            saveGenreToDb();
            savePlatformToDb();
            for(GameDto gameDto : games){
                saveDeveloperToDb(gameDto.getDevelopers());
                Game game = gameMapper.mapToEntity(gameDto);
                for(String platform : gameDto.getPlatforms()){
                    Platform platformFromDb = platformService.findByName(platform);
                    if(platformFromDb!=null){
                        game.addPlatform(platformFromDb);
                    }
                    else
                    {
                        game.addPlatform(new Platform(platform));
                    }
                }
                for(String developer : gameDto.getDevelopers()){
                    Developer developerFromDb = developerService.findByName(developer);
                    if(developerFromDb!=null){
                        game.addDeveloper(developerFromDb);
                    }
                    else{
                        game.addDeveloper(new Developer(developer));
                    }
                }

                for(String genre : gameDto.getGenres()){
                    Genre genreFromDb = genreService.findByName(genre);
                    if(genreFromDb!=null){
                        game.addGenre(genreFromDb);
                    }
                    else
                    {
                        game.addGenre(new Genre(genre));
                    }
                }

gameService.save(game);
            }


        }

    }
}
