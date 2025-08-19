package com.todolist.ToDoList.dto;
import lombok.Data;

@Data
public class CreateTaskDTO {
    private String name;
    private String description;
    private boolean status;
    private Long userId;
}