package ru.kinopoisk.server.services.mappers;

import org.springframework.stereotype.Component;
import ru.kinopoisk.server.models.entities.Article;
import ru.kinopoisk.server.models.dto.ArticleDto;

@Component
public class ArticleMapper {

    public Article mapToEntity(ArticleDto articleDto){

        Article article = new Article();

        article.setFullDescription(articleDto.getFullDescription());
        article.setSmallDescription(articleDto.getSmallDescription());
        article.setLink(articleDto.getLink());
        article.setName(articleDto.getName());
        article.setId(articleDto.getId());
        article.setRelease(articleDto.getRelease());
        article.setViews(articleDto.getViews());


        return article;

    }


}
