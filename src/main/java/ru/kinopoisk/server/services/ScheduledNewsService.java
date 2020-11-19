package ru.kinopoisk.server.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.kinopoisk.server.services.newsDbService.NewsService;

import java.io.IOException;
import java.text.ParseException;

@Component
public class ScheduledNewsService {

    @Autowired
    NewsService newsService;


    @Scheduled(fixedRate = 10000)
    public void retakeNews(){
        boolean isDropped=false;
        while(!isDropped)
        try {
            newsService.parser();
            isDropped=true;
            newsService.saveArticleToDb();
        } catch (IOException e) {
        } catch (ParseException e) {
        }

    }
}
