package ru.kinopoisk.server.services.gamesDbService;


import java.util.List;
import java.util.concurrent.ExecutionException;

public interface IGamesService {

    void savePlatformToDb();

    void saveDeveloperToDb(List<String> developers);

    void saveGenreToDb();

    void saveGamesToDb(int firstPageNumber, int lastPageNumber) throws ExecutionException, InterruptedException;



}
