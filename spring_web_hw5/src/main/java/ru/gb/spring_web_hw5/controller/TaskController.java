package ru.gb.spring_web_hw5.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.gb.spring_web_hw5.service.TaskService;
import ru.gb.spring_web_hw5.domain.Task;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService service;

    @GetMapping
    public List<Task> getAlltasks(){
        return service.getAllTasks();
    }

    @PostMapping
    public Task createTask(@RequestBody Task task) {
        return service.addTask(task.getDescription(), String.valueOf(task.getStatus()));
    }
}
