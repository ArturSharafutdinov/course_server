package ru.kinopoisk.server.services.mappers;

import org.springframework.stereotype.Component;
import ru.kinopoisk.server.persistence.domain.Article;
import ru.kinopoisk.server.persistence.dto.ArticleDto;

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

    public ArticleDto mapToDto(Article article){
        ArticleDto articleDto = new ArticleDto();
        articleDto.setName(article.getName());
        articleDto.setLink(article.getLink());
        articleDto.setFullDescription(article.getFullDescription());
        articleDto.setSmallDescription(article.getSmallDescription());
        articleDto.setRelease(article.getRelease());
        articleDto.setViews(article.getViews());
        articleDto.setId(article.getId());
        articleDto.setAuthor(article.getAuthor().getAuthorName());
        articleDto.setType(article.getSection().getSectionName());

        return articleDto;

    }


}
