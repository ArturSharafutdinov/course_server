package ru.kinopoisk.server.services.schedulers;


import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.scheduling.annotation.Scheduled;
        import org.springframework.stereotype.Component;
        import ru.kinopoisk.server.services.newsDbService.NewsService;

        import java.io.IOException;
        import java.text.ParseException;

@Component
public class ScheduledNewsUpdaterService  {

    @Autowired
    NewsService newsService;


    @Scheduled(fixedRate = 15000)
    public void updateViews(){
        newsService.updateAllArticlesViews();
    }
}