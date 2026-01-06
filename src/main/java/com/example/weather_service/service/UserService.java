package com.example.weather_service.service;

import com.example.weather_service.entity.RegisterUserRequest;
import com.example.weather_service.entity.UserResponse;
import com.example.weather_service.entity.Users;
import com.example.weather_service.repository.UserDetailsRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class UserService {

    private final UserDetailsRepository userDetailsRepository;
    private final PasswordEncoder passwordEncoder;


    public UserService(UserDetailsRepository userDetailsRepository, PasswordEncoder passwordEncoder) {
        this.userDetailsRepository = userDetailsRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserResponse registerUser(RegisterUserRequest registerUserRequest){

        if (userDetailsRepository.findByUsername(registerUserRequest.getUsername()).isPresent()){
            throw new RuntimeException("User is already exist");
        }

        Users users = new Users();
        users.setUsername(registerUserRequest.getUsername());
        users.setPassword(passwordEncoder.encode(registerUserRequest.getPassword()));
        users.setRole(registerUserRequest.getRole());
        Users savedUsers = userDetailsRepository.save(users);
        return new UserResponse(savedUsers.getId(), savedUsers.getUsername(), savedUsers.getRole().name());

    }
}
