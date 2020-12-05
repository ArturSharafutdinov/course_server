package ru.kinopoisk.server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kinopoisk.server.persistence.dao.DeveloperRepository;
import ru.kinopoisk.server.persistence.domain.Author;
import ru.kinopoisk.server.persistence.domain.Developer;

import java.util.List;

@Service
public class DeveloperService {

    @Autowired
    DeveloperRepository developerRepository;

    public void save(Developer developer){
        developerRepository.save(developer);
    }

    public List<Developer> getAll(){
       return developerRepository.findAll();
    }

    public Developer findByName(String name){
        return developerRepository.findByName(name);
    }

}
