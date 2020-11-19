package ru.kinopoisk.server.services.newsDbService;

import ru.kinopoisk.server.persistence.domain.Article;
import ru.kinopoisk.server.persistence.domain.Author;
import ru.kinopoisk.server.persistence.domain.Section;

import java.util.List;

public interface INewsService {

  void saveAuthorToDb(String authorName);

 void saveSectionToDb(String sectionName);

    boolean contains(String name, List<Article> articlesFromDb);

     void saveArticleToDb();

     List<Article> getAllArticles();

    List<Author> getAllAuthors();

    List<Section> getAllSections();




}
