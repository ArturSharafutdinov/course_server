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

        List<Platform> platforms = new ArrayList<>();
        for(String platform : gameDto.getPlatforms()){
            platforms.add(new Platform(platform));
        }

        List<Genre> genres = new ArrayList<>();
        for(String genre : gameDto.getGenres()){
            genres.add(new Genre(genre));
        }

        List<Developer> developers = new ArrayList<>();
        for(String developer : gameDto.getDevelopers()){
            developers.add(new Developer(developer));
        }

        return game;

    }



}
