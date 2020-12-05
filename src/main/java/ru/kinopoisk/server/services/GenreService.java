package ru.kinopoisk.server.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kinopoisk.server.persistence.dao.GenreRepository;
import ru.kinopoisk.server.persistence.domain.Developer;
import ru.kinopoisk.server.persistence.domain.Genre;

import java.util.List;

@Service
public class GenreService {

    @Autowired
    GenreRepository genreRepository;

    public void save(Genre genre){
        genreRepository.save(genre);
    }

    public List<Genre> getAll(){
        return genreRepository.findAll();
    }

    public Genre findByName(String name){
        return genreRepository.findByName(name);
    }

}
