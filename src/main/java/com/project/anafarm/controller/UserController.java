package com.project.anafarm.controller;

import com.project.anafarm.model.User;
import com.project.anafarm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) {
        User registeredUser = userService.registerUser(user);
        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        User loggedUser = userService.loginUser(user.getUsername(), user.getPassword());
        if (loggedUser != null) {
            return ResponseEntity.ok(loggedUser);
        }
        return ResponseEntity.badRequest().body("Usuário ou senha inválidos");
    }
}