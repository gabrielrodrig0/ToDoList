package com.todolist.ToDoList.dto;
import com.todolist.ToDoList.enums.Priority; 
import lombok.Data;

@Data
public class CreateTaskDTO {
    private String name;
    private String description;
    private Priority priority; 
}