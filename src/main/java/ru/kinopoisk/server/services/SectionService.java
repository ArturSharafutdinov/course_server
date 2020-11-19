package ru.kinopoisk.server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kinopoisk.server.persistence.dao.AuthorRepository;
import ru.kinopoisk.server.persistence.dao.SectionRepository;
import ru.kinopoisk.server.persistence.domain.Author;
import ru.kinopoisk.server.persistence.domain.Section;

import java.util.List;

@Service
public class SectionService {
    @Autowired
    SectionRepository sectionRepository;

    public List<Section> getAll(){
        return sectionRepository.findAll();
    }

    public void save(Section section){
       sectionRepository.save(section);
    }

    public Section findByName(String name){
        return sectionRepository.findByName(name);
    }

}
