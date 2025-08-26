package com.todolist.ToDoList.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.todolist.ToDoList.dto.CreateTaskDTO;
import com.todolist.ToDoList.model.Task;
import com.todolist.ToDoList.model.User;
import com.todolist.ToDoList.repository.TaskRepository;
import com.todolist.ToDoList.repository.UserRepository;

import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository; // ou UserRepository

    public Task createTask(CreateTaskDTO createTaskDTO, Long userId) {

        if(createTaskDTO.getName() == null || createTaskDTO.getName().isEmpty()) {
            throw new RuntimeException("Task name cannot be empty");
        }

        if(createTaskDTO.getDescription() == null || createTaskDTO.getDescription().isEmpty()) {
            throw new RuntimeException("Task description cannot be empty");
        }

        if(createTaskDTO.getPriority() == null) {
            throw new RuntimeException("Task priority cannot be null");
        }
        Optional<User> user = userRepository.findById(userId);
        if(!user.isPresent()) {
            throw new RuntimeException("User not found");
        }

        Task task = new Task();
        task.setDescription(createTaskDTO.getDescription());
        task.setName(createTaskDTO.getName());
        task.setStatus(false);
        task.setPriority(createTaskDTO.getPriority());

        task.setUser(user.get());
        return taskRepository.save(task);
    }

    public Optional<Task> getTaskById(Long id) {
        return taskRepository.findById(id);
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }
}