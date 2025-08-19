package com.todolist.ToDoList.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.todolist.ToDoList.dto.CreateUserDTO;
import com.todolist.ToDoList.enums.Role;
import com.todolist.ToDoList.model.User;
import com.todolist.ToDoList.repository.UserRepository;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(CreateUserDTO createUserDTO) {

        if (userRepository.existsByUsername(createUserDTO.getUsername())) {
            throw new RuntimeException("Username already exists");
        }

        if (createUserDTO.getRole() == null) {
            createUserDTO.setRole(Role.USER); 
        }

        if (createUserDTO.getPassword() == null || createUserDTO.getPassword().isEmpty()) {
            throw new RuntimeException("Password cannot be empty");
        }

        if (createUserDTO.getEmail() == null || createUserDTO.getEmail().isEmpty()) {
            throw new RuntimeException("Email cannot be empty");
        }

        if (createUserDTO.getUsername() == null || createUserDTO.getUsername().isEmpty()) {
            throw new RuntimeException("Username cannot be empty");
        }

        createUserDTO.setRole(createUserDTO.getRole() != null ? createUserDTO.getRole() : Role.USER); 
        User user = new User();
        user.setUsername(createUserDTO.getUsername());
        user.setPassword(createUserDTO.getPassword());
        user.setEmail(createUserDTO.getEmail());
        user.setRole(createUserDTO.getRole());

        return userRepository.save(user);
    }

    public Optional<User> getUserById(Long id) {
        if (id == null) {
            throw new RuntimeException("User ID cannot be null");
        }
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User not found");
        }
        return userRepository.findById(id);
    }

    public User updateUser(Long id, User userDetails) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        user.setUsername(userDetails.getUsername());
        user.setPassword(userDetails.getPassword());
        user.setEmail(userDetails.getEmail());
        user.setRole(userDetails.getRole());
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public Optional<User> deleteUserById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteUserById'");
    }

}