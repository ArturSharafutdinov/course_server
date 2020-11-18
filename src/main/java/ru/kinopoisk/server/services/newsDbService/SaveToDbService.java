package ru.kinopoisk.server.services.newsDbService;


import ru.kinopoisk.server.models.entities.Article;
import ru.kinopoisk.server.models.entities.Author;
import ru.kinopoisk.server.models.entities.Section;
import ru.kinopoisk.server.services.mappers.ArticleMapper;
import ru.kinopoisk.server.models.dto.ArticleDto;
import ru.kinopoisk.server.parsers.IgromaniaNewsParser;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public class SaveToDbService {

    // List of articles from newsParser
    private static List<ArticleDto> articles;

    public static EntityManager em = Persistence.createEntityManagerFactory("COLIBRI").createEntityManager();

    public SaveToDbService(List<ArticleDto> articlesFromParser) {
        articles = articlesFromParser;
    }

    // Save authors from articleDto field author
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
                 em.persist(newAuthor);
                   em.getTransaction().commit();
           }

       }
       else
       {
           Author newAuthor = new Author();
           newAuthor.setAuthorName(authorName);
           em.getTransaction().begin();
        em.persist(newAuthor);
           em.getTransaction().commit();
       }

    }

    // Save sections from articleDto field author
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
              em.persist(newSection);
                em.getTransaction().commit();
            }

        }
        else
        {
            Section newSection = new Section();
            newSection.setSectionName(sectionName);
            em.getTransaction().begin();
        em.persist(newSection);
            em.getTransaction().commit();
        }

    }

    // Check if db.Article contains article with parameter name
    public static boolean contains(String name, List<Article> articlesFromDb){
        for(Article article : articlesFromDb){
            if(article.getName().equals(name))
                return true;
        }
        return false;
    }

    public static void saveArticleToDb() {
        ArticleMapper mapper = new ArticleMapper();
        List<Article> articlesFromDb = em.createNamedQuery("Article.findAll").getResultList();
        if (!articlesFromDb.isEmpty()) {

            boolean isHave = false; // Проверка на наличие в бд

            for (ArticleDto articleDto : articles) {
                if(contains(articleDto.getName(),articlesFromDb)){ // Полностью проходимся по бд
                   isHave=true;
                }
                if(!isHave){
                 saveAuthorToDb(articleDto.getAuthor());
                   saveSectionToDb(articleDto.getType());
                    Article article = mapper.mapToEntity(articleDto);
                    Query q = em.createNamedQuery("Author.findByName");
                    q.setParameter(1, articleDto.getAuthor());
                    Author author = (Author)q.getResultList().get(0);
                    article.setAuthor(author);

                    Query q2 = em.createNamedQuery("Section.findByName");
                    q2.setParameter(1, articleDto.getType());
                    Section section = (Section)q2.getResultList().get(0);
                    article.setSection(section);
                    em.getTransaction().begin();
                    em.persist(article);
                    em.getTransaction().commit();
                }

            }
        } else {
            for (ArticleDto articleDto : articles) {
                saveAuthorToDb(articleDto.getAuthor());
                saveSectionToDb(articleDto.getType());

                Article article = mapper.mapToEntity(articleDto);

                Query q = em.createNamedQuery("Author.findByName");
                q.setParameter(1, articleDto.getAuthor());
                Author author = (Author)q.getResultList().get(0);
               article.setAuthor(author);

                Query q2 = em.createNamedQuery("Section.findByName");
                q2.setParameter(1, articleDto.getType());
                Section section = (Section)q2.getResultList().get(0);
                article.setSection(section);

                em.getTransaction().begin();
               em.persist(article);
                em.getTransaction().commit();
            }
        }
    }


    // Print all info from database section related to news
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
      //  IgromaniaNewsParser.showAllArticles();
        SaveToDbService saveToDbService = new SaveToDbService(IgromaniaNewsParser.getAllArticles());
       saveArticleToDb();
        showAllInfo();
    }

}
