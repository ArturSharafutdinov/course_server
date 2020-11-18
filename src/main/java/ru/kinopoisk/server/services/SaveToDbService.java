package ru.kinopoisk.server.services;


import org.h2.engine.Session;
import org.springframework.data.jpa.provider.HibernateUtils;
import ru.kinopoisk.server.entities.Article;
import ru.kinopoisk.server.entities.Author;
import ru.kinopoisk.server.entities.Section;
import ru.kinopoisk.server.mappers.ArticleMapper;
import ru.kinopoisk.server.models.ArticleDto;
import ru.kinopoisk.server.models.AuthorDto;
import ru.kinopoisk.server.parsers.IgromaniaNewsParser;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SaveToDbService {

    private static List<ArticleDto> articles;

    public static EntityManager em = Persistence.createEntityManagerFactory("COLIBRI").createEntityManager();

    public SaveToDbService(List<ArticleDto> articlesFromParser) {
        articles = articlesFromParser;
    }

    public static void saveAuthorToDb(String authorName){
       List<Author> authors = em.createNamedQuery("Author.findAll").getResultList();
       if(!authors.isEmpty()){
           boolean isHave = false;
           for (Author author : authors){
            if(author.getAuthorName().equals(authorName)){
                isHave=true;
            }
           }
           if(!isHave){
                   Author newAuthor = new Author();
                   newAuthor.setAuthorName(authorName);
                   em.getTransaction().begin();
                   Author authorFromDb = em.merge(newAuthor);
                   em.getTransaction().commit();
           }

       }
       else
       {
           Author newAuthor = new Author();
           newAuthor.setAuthorName(authorName);
           em.getTransaction().begin();
           Author authorFromDb = em.merge(newAuthor);
           em.getTransaction().commit();
       }

    }
    public static void saveSectionToDb(String sectionName){
        List<Section> sections = em.createNamedQuery("Section.findAll").getResultList();
        if(!sections.isEmpty()){
            boolean isHave = false;
            for (Section section : sections){
                if(section.getSectionName().equals(sectionName)){
                    isHave=true;
                }
            }
            if(!isHave){
               Section newSection = new Section();
                newSection.setSectionName(sectionName);
                em.getTransaction().begin();
                Section sectionFromDb = em.merge(newSection);
                em.getTransaction().commit();
            }

        }
        else
        {
            Section newSection = new Section();
            newSection.setSectionName(sectionName);
            em.getTransaction().begin();
            Section sectionFromDb = em.merge(newSection);
            em.getTransaction().commit();
        }

    }

    public static boolean contains(String name, List<Article> articlesFromDb){
        for(Article article : articlesFromDb){
            if(article.getName().equals(name))
                return true;
        }
        return false;
    }

    public static void saveArticleToDb(){
        ArticleMapper mapper = new ArticleMapper();
        List<Article> articlesFromDb = em.createNamedQuery("Article.findAll").getResultList();
        if(!articlesFromDb.isEmpty()){

            boolean isHave = false; // Проверка на наличие в бд

            for(ArticleDto articleDto : articles){
                if(contains(articleDto.getName(),articlesFromDb)){ // Полностью проходимся по бд
                   isHave=true;
                }
                if(!isHave){
                    saveAuthorToDb(articleDto.getAuthor());
                 //   saveSectionToDb(articleDto.getType());
//                    Article article = mapper.mapToEntity(articleDto);
//                    em.getTransaction().begin();
//                    Article articleFromDb = em.merge(article);
//                    em.getTransaction().commit();
                }

            }
        }
        else{
//            for (ArticleDto articleDto : articles){
//                saveAuthorToDb(articleDto.getAuthor());
//                saveSectionToDb(articleDto.getType());
//                Article article = mapper.mapToEntity(articleDto);
//                em.getTransaction().begin();
//               Article articleFromDb = em.merge(article);
//                em.getTransaction().commit();
            }
        }

    }

    public static void showAllInfo() {
        System.out.println("Articles: ");
        List<Article> articlesFromDb = em.createNamedQuery("Article.findAll").getResultList();

        for (Article article : articlesFromDb) {
            System.out.println(article);
        }

        System.out.println();
        System.out.println("Authors: ");
        List<Author> authorsFromDb = em.createNamedQuery("Author.findAll").getResultList();
        for (Author author : authorsFromDb) {
            System.out.println(author);
        }

        System.out.println();
        System.out.println("Sections: ");
        List<Section> sectionsFromDb = em.createNamedQuery("Section.findAll").getResultList();
        for (Section section : sectionsFromDb) {
            System.out.println(section);
        }
    }





    public static void main(String[] args) throws IOException, ParseException {
        IgromaniaNewsParser parser = new IgromaniaNewsParser();
        parser.getInfoFromNews();
        parser.showAllArticles();
        SaveToDbService saveToDbService = new SaveToDbService(IgromaniaNewsParser.getAllArticles());
       saveArticleToDb();
        showAllInfo();
    }

}
