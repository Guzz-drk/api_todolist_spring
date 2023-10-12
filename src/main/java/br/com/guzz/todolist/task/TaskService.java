package br.com.guzz.todolist.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class TaskService {
    
    @Autowired
    private TaskRepository taskRepository;

    public ResponseEntity<?> createTask(TaskModel taskModel){
        var task = this.taskRepository.save(taskModel);
        return ResponseEntity.status(201).body(task);
    }
}
