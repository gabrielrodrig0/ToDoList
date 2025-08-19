package com.todolist.ToDoList.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todolist.ToDoList.model.Task;
import com.todolist.ToDoList.repository.TaskRepository;

import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    public Optional<Task> getTaskById(Long id) {
        return taskRepository.findById(id);
    }

    // public Task updateTask(Long id, Task taskDetails) {
    //     Task task = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
    //     task.setTitle(taskDetails.getTitle());
    //     task.setDescription(taskDetails.getDescription());
    //     task.setDueDate(taskDetails.getDueDate());
    //     return taskRepository.save(task);
    // }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }
}