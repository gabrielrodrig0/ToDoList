package com.todolist.ToDoList.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.todolist.ToDoList.dto.CreateUserDTO;
import com.todolist.ToDoList.service.UserService;

@RestController
public class AuthController {
    
    @Autowired
    UserService userService;
    
    @PostMapping("/auth/register")
    public ResponseEntity<?> register(@RequestBody CreateUserDTO createUserDTO) {
        try {
            return ResponseEntity.ok(userService.createUser(createUserDTO));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    

}
