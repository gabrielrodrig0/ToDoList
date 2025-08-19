package com.todolist.ToDoList.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.todolist.ToDoList.dto.CreateTaskDTO;
import com.todolist.ToDoList.model.Task;
import com.todolist.ToDoList.model.User;
import com.todolist.ToDoList.repository.UserRepository;
import com.todolist.ToDoList.service.TaskService;

@RestController
public class TaskController {

    @Autowired
    private TaskService taskService;
    
    @Autowired
    private UserRepository userRepository; // ou UserRepository

    public ResponseEntity<?> createTask(CreateTaskDTO createTaskDTO, @PathVariable Long userId) {
        Task task = new Task();
        task.setDescription(createTaskDTO.getDescription());
        task.setName(createTaskDTO.getName());
        task.setStatus(false);
        Optional<User> user = userRepository.findById(userId); 
        if (!user.isPresent()) {
            return ResponseEntity.badRequest().body("User not found");
        }
        task.setUser(user.get());

        Task createdTask = taskService.createTask(task);
        return ResponseEntity.ok(createdTask);
    }
    
}
