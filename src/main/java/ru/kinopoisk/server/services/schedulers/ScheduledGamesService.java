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


    @Scheduled(fixedRate = 432000000) //5 days
    public void retakeGames() throws ExecutionException, InterruptedException {
        long startTime = System.currentTimeMillis();
        gamesService.saveGamesToDb(1,25548);
        long timeSpent = System.currentTimeMillis() - startTime;
        System.out.println("программа выполнялась " + timeSpent + "миллисекунд");
        System.out.println("программа выполнялась " + (timeSpent/1000.0) + " секунд");
    }
}