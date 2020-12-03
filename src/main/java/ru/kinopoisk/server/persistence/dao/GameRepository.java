package ru.kinopoisk.server.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kinopoisk.server.persistence.domain.Game;

@Repository
public interface GameRepository extends JpaRepository<Game,Long> {
}
