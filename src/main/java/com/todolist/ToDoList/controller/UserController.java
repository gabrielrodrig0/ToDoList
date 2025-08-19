package com.todolist.ToDoList.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.todolist.ToDoList.dto.CreateUserDTO;
import com.todolist.ToDoList.service.UserService;

@RestController
public class UserController {
    
    @Autowired
    UserService userService;
    
    @PostMapping("/users")
    public ResponseEntity<?> createUser(@RequestBody CreateUserDTO createUserDTO) {
        try {
            return ResponseEntity.ok(userService.createUser(createUserDTO));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
