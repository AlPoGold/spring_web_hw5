package ru.gb.spring_web_hw5.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gb.spring_web_hw5.domain.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {

}
