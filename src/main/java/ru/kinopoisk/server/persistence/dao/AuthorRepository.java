package ru.kinopoisk.server.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kinopoisk.server.persistence.domain.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author,Long> {
    Author findByName(String name);
}
