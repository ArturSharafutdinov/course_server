package ru.kinopoisk.server.services.mappers;


import org.springframework.stereotype.Component;
import ru.kinopoisk.server.persistence.domain.Developer;
import ru.kinopoisk.server.persistence.domain.Game;
import ru.kinopoisk.server.persistence.domain.Genre;
import ru.kinopoisk.server.persistence.domain.Platform;
import ru.kinopoisk.server.persistence.dto.GameDto;

import java.util.ArrayList;
import java.util.List;

@Component
public class GameMapper {

    public Game mapToEntity(GameDto gameDto){

        Game game = new Game();

        game.setOriginalName(gameDto.getOriginalName());
        game.setDescription(gameDto.getDescription());
        game.setGamesSeries(gameDto.getGamesSeries());
        game.setImageLink(gameDto.getImageLink());
        game.setLink(gameDto.getLink());
        game.setMetacriticRating(gameDto.getMetacriticRating());
        game.setReleaseDate(gameDto.getReleaseDate());

//        for(String platform : gameDto.getPlatforms()){
//            game.addPlatform(new Platform(platform));
//        }
//
//        for(String genre : gameDto.getGenres()){
//            game.addGenre(new Genre(genre));
//        }
//
//        for(String developer : gameDto.getDevelopers()){
//           game.addDeveloper(new Developer(developer));
//        }

        return game;

    }



}
