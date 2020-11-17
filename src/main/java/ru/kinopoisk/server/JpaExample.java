package ru.kinopoisk.server;

import org.springframework.beans.factory.annotation.Autowired;
import ru.kinopoisk.server.entities.Article;
import ru.kinopoisk.server.entities.Author;
import ru.kinopoisk.server.repositories.AuthorRepository;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.swing.text.html.parser.Entity;

public class JpaExample {

    public static EntityManager em = Persistence.createEntityManagerFactory("COLIBRI").createEntityManager();

    public static void main(String[] args) {

        Author author = new Author();
        author.setAuthorName("First Name");

        em.getTransaction().begin();
        Author authorFromDb = em.merge(author);
        em.getTransaction().commit();


        System.out.println(em.find(Author.class,(long)1).getAuthorName());


    }
}
