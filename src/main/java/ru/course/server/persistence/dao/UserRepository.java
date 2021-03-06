package ru.course.server.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.course.server.persistence.domain.User;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByEmail(String email);

    User findByIdEquals (Long Id);

    Long countUserByEmail(String email);
}

