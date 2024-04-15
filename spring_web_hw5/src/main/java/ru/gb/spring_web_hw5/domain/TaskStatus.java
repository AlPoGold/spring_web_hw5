package ru.gb.spring_web_hw5.domain;

public enum TaskStatus {
    NOT_STARTED,
    IN_PROGRESS,
    COMPLETED;

    public static TaskStatus fromString(String status) {
        return TaskStatus.valueOf(status.toUpperCase());
    }
}
