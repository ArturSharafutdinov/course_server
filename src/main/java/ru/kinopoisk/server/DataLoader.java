package ru.kinopoisk.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import ru.kinopoisk.server.services.ArticleService;
import ru.kinopoisk.server.services.AuthorService;
import ru.kinopoisk.server.services.SectionService;
import ru.kinopoisk.server.services.newsDbService.NewsService;

import java.io.IOException;
import java.text.ParseException;

@Component
public class DataLoader implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    AuthorService authorService;
    @Autowired
    SectionService sectionService;
    @Autowired
    ArticleService articleService;
    @Autowired
    NewsService newsService;


    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        try {
            newsService.parser();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        newsService.saveArticleToDb();

    }

}