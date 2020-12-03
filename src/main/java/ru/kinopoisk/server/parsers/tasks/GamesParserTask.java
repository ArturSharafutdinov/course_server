package ru.kinopoisk.server.parsers.tasks;

import ru.kinopoisk.server.parsers.IgromaniaGamesParser;
import ru.kinopoisk.server.persistence.domain.Game;
import ru.kinopoisk.server.persistence.dto.GameDto;
import ru.kinopoisk.server.services.mappers.GameMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class GamesParserTask implements Callable<List<Game>> {

    int pageNumber;

    List<GameDto> games;

    public GamesParserTask(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    @Override
    public List<Game> call() throws Exception {
        games = new IgromaniaGamesParser(pageNumber).getGamesInfoFromPageWithNumber();
           if(games.size()!=0){
               List<Game> gamesList = new ArrayList<>();
               GameMapper gameMapper = new GameMapper();
               for(GameDto gameDto : games){
                   gamesList.add(gameMapper.mapToEntity(gameDto));
               }
               return gamesList;
           }
           return null;
    }
}
