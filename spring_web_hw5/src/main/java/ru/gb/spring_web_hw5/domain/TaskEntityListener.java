package ru.gb.spring_web_hw5.domain;

import jakarta.persistence.PrePersist;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class TaskEntityListener {
    @PrePersist
    public void prePersist(Task task) {
        System.out.println("вызов метода пресист");
        task.setCreatingTime(LocalDateTime.now());
    }
}
