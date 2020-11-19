package ru.kinopoisk.server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kinopoisk.server.persistence.dao.ArticleRepository;
import ru.kinopoisk.server.persistence.domain.Article;
import java.util.List;

@Service
public class ArticleService {
    @Autowired
    ArticleRepository articleRepository;

    public List<Article> getAll(){
        return articleRepository.findAll();
    }

    public void save(Article article){
       articleRepository.save(article);
    }

}
