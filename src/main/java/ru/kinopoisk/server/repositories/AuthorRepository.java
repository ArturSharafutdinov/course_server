package ru.kinopoisk.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import ru.kinopoisk.server.models.entities.Author;

@Component
public interface AuthorRepository extends JpaRepository<Author,Long> {

}
