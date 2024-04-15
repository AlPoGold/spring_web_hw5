package ru.gb.spring_web_hw5.domain;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.PrePersist;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@EntityListeners(TaskEntityListener.class)
public class TaskEntityListener {
    @PrePersist
    public void prePersist(Task task) {
        task.setCreatingTime(LocalDateTime.now());
    }
}
