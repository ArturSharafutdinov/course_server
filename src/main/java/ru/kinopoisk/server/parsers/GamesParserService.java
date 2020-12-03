package ru.kinopoisk.server.parsers;

import ru.kinopoisk.server.parsers.tasks.GamesParserTask;
import ru.kinopoisk.server.persistence.domain.Game;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class GamesParserService {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(12);
        List<Future<List<Game>>> futures = new ArrayList<Future<List<Game>>>(1000);
        long startTime = System.currentTimeMillis();
        for(int i=1;i<25548;i++){
       GamesParserTask gamesParserThread = new GamesParserTask(i);
     futures.add(executorService.submit(gamesParserThread));
        }
        int counter = 0;
        for (Future<List<Game>> f : futures) {
            List<Game> games = f.get();
            if(games!=null){
               counter+= games.size();
            }
        }
        System.out.println(counter);
        long timeSpent = System.currentTimeMillis() - startTime;
 System.out.println("программа выполнялась " + timeSpent + " миллисекунд");
        executorService.shutdown();

    }

}
