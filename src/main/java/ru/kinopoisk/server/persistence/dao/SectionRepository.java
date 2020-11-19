package ru.kinopoisk.server.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kinopoisk.server.persistence.domain.Section;

@Repository
public interface SectionRepository extends JpaRepository<Section,Long> {
   Section findByName(String name);
}
