package ru.kinopoisk.server.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kinopoisk.server.persistence.dto.ArticleDto;
import ru.kinopoisk.server.services.mappers.ArticleMapper;
import ru.kinopoisk.server.services.newsDbService.NewsService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class NewsController {

    private final NewsService newsService;

    private final ArticleMapper articleMapper;

    public NewsController(NewsService newsService, ArticleMapper articleMapper) {
        this.newsService = newsService;
        this.articleMapper = articleMapper;
    }

    @GetMapping("/news")
    List<ArticleDto> allNews(){
        return newsService.getAllArticles().stream().map(article -> articleMapper.mapToDto(article)).collect(Collectors.toList());
    }

}
