package com.todolist.ToDoList.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.todolist.ToDoList.dto.CreateTaskDTO;
import com.todolist.ToDoList.model.Task;
import com.todolist.ToDoList.service.JwtService;
import com.todolist.ToDoList.service.TaskService;
import jakarta.servlet.http.HttpServletRequest;

@RestController
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private JwtService jwtService;
    
    @PostMapping("/tasks")
    public ResponseEntity<?> createTask(@RequestBody CreateTaskDTO createTaskDTO, HttpServletRequest request) {
        try {
            String header = request.getHeader("Authorization");
            String token = header.replace("Bearer ", "");
            Long authenticatedUserId = jwtService.getUserIdFromToken(token);

            System.out.println("Authenticated User ID: " + authenticatedUserId); // Log do ID do usuário autenticado
            Task createdTask = taskService.createTask(createTaskDTO, authenticatedUserId);
            return ResponseEntity.ok(createdTask); 
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
