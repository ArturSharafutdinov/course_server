package ru.kinopoisk.server.mappers;

import org.springframework.stereotype.Component;
import ru.kinopoisk.server.entities.Article;
import ru.kinopoisk.server.entities.Author;
import ru.kinopoisk.server.entities.Section;
import ru.kinopoisk.server.models.ArticleDto;

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


        Author author = new Author();
        author.setAuthorName(articleDto.getAuthor());

        Section section = new Section();
        section.setSectionName(articleDto.getType());

        article.setAuthor(author);
        article.setSection(section);

        return article;

    }


}
