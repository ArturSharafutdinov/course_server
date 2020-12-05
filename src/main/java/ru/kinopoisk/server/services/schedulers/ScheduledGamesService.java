package ru.kinopoisk.server.services.schedulers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.kinopoisk.server.services.gamesDbService.GamesService;

import java.util.concurrent.ExecutionException;

@Component
public class ScheduledGamesService {

    @Autowired
    GamesService gamesService;


    @Scheduled(fixedRate = 1500000)
    public void retakeGames() throws ExecutionException, InterruptedException {
        gamesService.saveGamesToDb(20000,25000);

    }
}