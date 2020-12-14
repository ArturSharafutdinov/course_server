package ru.course.server.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.course.server.persistence.domain.Task;


public interface TaskRepository extends JpaRepository<Task,Long> {

    Task findByName(String name);

}
