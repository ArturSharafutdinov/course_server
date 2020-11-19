package ru.kinopoisk.server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kinopoisk.server.persistence.dao.AuthorRepository;
import ru.kinopoisk.server.persistence.domain.Author;

import java.util.List;

@Service
public class AuthorService {

    @Autowired
    AuthorRepository authorRepository;

    public List<Author> getAll(){
        return authorRepository.findAll();
    }

    public void save(Author author){
        authorRepository.save(author);
    }

    public Author findByName(String name){
        return authorRepository.findByName(name);
    }

}