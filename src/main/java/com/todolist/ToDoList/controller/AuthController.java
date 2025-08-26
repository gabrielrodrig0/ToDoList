package com.todolist.ToDoList.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.todolist.ToDoList.dto.CreateUserDTO;
import com.todolist.ToDoList.dto.LoginRequestDTO;
import com.todolist.ToDoList.dto.LoginResponseDTO;
import com.todolist.ToDoList.model.CustomUserDetails;
import com.todolist.ToDoList.service.JwtService;
import com.todolist.ToDoList.service.UserService;

@RestController
public class AuthController {

    @Autowired
    AuthenticationManager authManager;
    
    @Autowired
    UserService userService;

    @Autowired
    JwtService jwtService;
    
    @PostMapping("/auth/register")
    public ResponseEntity<?> register(@RequestBody CreateUserDTO createUserDTO) {
        try {
            return ResponseEntity.ok(userService.createUser(createUserDTO));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/auth/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO loginDTO) {
        
        Authentication auth = (Authentication) authManager.authenticate(
        new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword()));

        CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();
        // Pega o ID do usuário
        Long userId = userDetails.getId(); 
        // Gera token pelo ID
        String token = jwtService.generateToken(userId);

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    

}

