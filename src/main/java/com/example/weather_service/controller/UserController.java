package com.example.weather_service.controller;

import com.example.weather_service.entity.RegisterUserRequest;
import com.example.weather_service.entity.Role;
import com.example.weather_service.entity.UserResponse;
import com.example.weather_service.entity.Users;
import com.example.weather_service.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponse> registerUser(@RequestBody RegisterUserRequest registerUserRequest){

        registerUserRequest.setRole(Role.USER);
        UserResponse userResponse = userService.registerUser(registerUserRequest);
        return ResponseEntity.ok(userResponse);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/registerByAdmin")
    public ResponseEntity<UserResponse> registerUserByAdmin(@RequestBody RegisterUserRequest registerUserRequest){
        UserResponse userResponse = userService.registerUser(registerUserRequest);
        return ResponseEntity.ok(userResponse);
    }

}
