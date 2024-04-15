package ru.gb.spring_web_hw5.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.spring_web_hw5.domain.NoTaskException;
import ru.gb.spring_web_hw5.domain.Task;
import ru.gb.spring_web_hw5.repository.TaskRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class TaskService {
    private final TaskRepository repository;

    public List<Task> getAllTasks(){
        return repository.findAll();
    }

    public Task getTaskById(Long id){
        return repository.findById(id).orElse(null);
    }

    public Task addTask(String description, String status){
        Task task = new Task();
        task.setDescription(description);
        task.setStatus(status);
        return repository.save(task);


    }

    public void deleteTask(Task task){
        repository.deleteById(task.getId());
    }

    public Task updateTask(Long id, Task updatedTask){
        Task oldTask = repository.findById(id).orElse(null);
        if(oldTask!=null){
            oldTask.setDescription(updatedTask.getDescription());
            oldTask.setStatus(String.valueOf(updatedTask.getStatus()));
            oldTask.setCreatingTime(updatedTask.getCreatingTime());
            return repository.save(oldTask);
        }else{throw new NoTaskException("Task with id = " + id + " has not been found in database");}

    }
}
