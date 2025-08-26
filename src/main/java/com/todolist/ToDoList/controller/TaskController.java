package com.todolist.ToDoList.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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
    
    // @PatchMapping("/tasks/{taskId}/toggle")
    // public ResponseEntity<?> toggleTaskStatus(@RequestBody Long taskId) {
    //     try {
    //         Task task = taskService.getTaskById(taskId)
    //             .orElseThrow(() -> new RuntimeException("Task not found"));

    //         task.setStatus(!task.getStatus());
    //         Task updatedTask = taskService.createTask(
    //             new CreateTaskDTO(task.getName(), task.getDescription(), task.getPriority()), 
    //             task.getUser().getId()
    //         );

    //         return ResponseEntity.ok(updatedTask);
    //     } catch (Exception e) {
    //         return ResponseEntity.badRequest().body(e.getMessage());
    //     }
    // }

    // @DeleteMapping("/tasks/{taskId}")
    // public ResponseEntity<?> deleteTask(@RequestBody Long taskId, HttpServletRequest request) {
    //     try {
    //         String header = request.getHeader("Authorization");
    //         String token = header.replace("Bearer ", "");
    //         Long authenticatedUserId = jwtService.getUserIdFromToken(token);
    //         taskService.deleteTask(taskId, authenticatedUserId);
    //         return ResponseEntity.ok("Task deleted successfully");
    //     } catch (Exception e) {
    //         return ResponseEntity.badRequest().body(e.getMessage());
    //     }
    // }
}
