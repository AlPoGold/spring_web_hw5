package ru.gb.spring_web_hw5.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@EntityListeners(TaskEntityListener.class)
@Table(name="tasks")
@Data
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String description;

    private TaskStatus status;

    @Column(name="created")
    private LocalDateTime creatingTime;

//    @Transient
//    private TaskEntityListener taskEntityListener;

    public void setStatus(String status) {
        this.status = TaskStatus.fromString(status);
    }



}
