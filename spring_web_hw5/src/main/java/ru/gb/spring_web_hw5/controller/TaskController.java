package ru.gb.spring_web_hw5.controller;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Metrics;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.gb.spring_web_hw5.service.FileGateway;
import ru.gb.spring_web_hw5.service.TaskService;
import ru.gb.spring_web_hw5.domain.Task;
import ru.gb.spring_web_hw5.domain.TaskStatus;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/tasks")
public class TaskController {
    private final Counter addTaskCounter = Metrics.counter("add_task_count");
    private final TaskService service;
    private final FileGateway fileGateway;

    @GetMapping
    public List<Task> getAlltasks(){
        return service.getAllTasks();
    }

    @PostMapping
    public Task createTask(@RequestBody Task task) {
        addTaskCounter.increment();

        //INTEGRATION PART



        Task addedTask = service.addTask(task.getDescription(), String.valueOf(task.getStatus()));
        fileGateway.writeToFile(addedTask.getCreatingTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss")) +".txt", addedTask);
        return addedTask;
    }

    @GetMapping("/status/{status}")
    public List<Task> getTasksByStatus(@PathVariable String status){
        return service.getTaskByStatus(status.toUpperCase());
    }

    @PutMapping("/{id}")
    public Task updateTaskStatus(@PathVariable Long id, @RequestBody Task task){
        return service.updateTask(id, task);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id){
        service.deleteTask(service.getTaskById(id));
    }
}
