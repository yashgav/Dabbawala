package com.example.dabbawala.controller;

import com.example.dabbawala.model.User;
import com.example.dabbawala.service.UserService;
import com.example.dabbawala.dto.LoginResponse;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return userService.registerUser(user);
    }

    @GetMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestParam String phone, @RequestParam String password) {
        Optional<User> user = userService.findByPhone(phone);
        if (user.isPresent() && user.get().getPassword().equals(password)) {
            User u = user.get();
            LoginResponse response = new LoginResponse(u.getId(), u.getRole().name());
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }
}
