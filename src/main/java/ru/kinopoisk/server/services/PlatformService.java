package ru.kinopoisk.server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kinopoisk.server.persistence.dao.PlatformRepository;
import ru.kinopoisk.server.persistence.domain.Developer;
import ru.kinopoisk.server.persistence.domain.Platform;

import java.util.List;

@Service
public class PlatformService {

    @Autowired
    PlatformRepository platformRepository;

    public void save(Platform platform){
        platformRepository.save(platform);
    }

    public List<Platform> getAll(){
      return  platformRepository.findAll();
    }

    public Platform findByName(String name){
        return platformRepository.findByName(name);
    }

}
