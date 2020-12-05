package ru.kinopoisk.server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kinopoisk.server.persistence.dao.GameRepository;
import ru.kinopoisk.server.persistence.domain.Developer;
import ru.kinopoisk.server.persistence.domain.Game;

import java.util.List;

@Service
public class GameService {

    @Autowired
    GameRepository gameRepository;


    public void save(Game game){
        gameRepository.save(game);
    }

    public List<Game> getAll(){
       return gameRepository.findAll();
    }

    public Game findByName(String name){
        return gameRepository.findByOriginalName(name);
    }


}
