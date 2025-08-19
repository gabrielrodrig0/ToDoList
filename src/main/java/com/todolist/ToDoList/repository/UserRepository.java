package com.todolist.ToDoList.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.todolist.ToDoList.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByUsername(String username);
}