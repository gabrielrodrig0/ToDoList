package com.todolist.ToDoList.dto;
import com.todolist.ToDoList.enums.Role;
import lombok.Data;

@Data
public class CreateUserDTO {
    private String username;
    private String password;
    private String email;
    private Role role;
}
