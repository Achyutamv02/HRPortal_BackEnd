package com.example.demo.HR.Controller;

import com.example.demo.HR.Model.UserModel;
import com.example.demo.HR.Repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public UserModel login(@RequestBody UserModel loginUser) {
        return userRepository.findByEmailAndPassword(loginUser.getEmail(), loginUser.getPassword());
    }

    @PostMapping("/register")
    public String register(@RequestBody UserModel newUser) {
        System.out.println("Registering user with role: " + newUser.getRole()); // DEBUG
        try {
            int rows = userRepository.createUser(newUser);
            return rows == 1 ? "User created!" : "Failed to create user.";
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed to create user: " + e.getMessage();
        }
    }



}
