package ru.kinopoisk.server.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.kinopoisk.server.persistence.domain.Author;
import ru.kinopoisk.server.persistence.domain.Section;

@Repository
public interface SectionRepository extends JpaRepository<Section,Long> {
   Section findByName(String name);
}
