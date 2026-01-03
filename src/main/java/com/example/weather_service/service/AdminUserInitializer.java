package com.example.weather_service.service;

import com.example.weather_service.entity.Users;
import com.example.weather_service.repository.UserDetailsRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AdminUserInitializer {

    @Bean
    public CommandLineRunner createAdminUser(UserDetailsRepository userDetailsRepository,
                                             PasswordEncoder passwordEncoder) {
        return args -> {
            if (userDetailsRepository.findByUsername("Rushi").isEmpty()) {
                Users user = new Users();
                user.setUsername("Rushi");
                user.setPassword(passwordEncoder.encode("Rushi123"));
                user.setRole("ROLE_ADMIN"); // must start with ROLE_ for Spring Security

                userDetailsRepository.save(user);
                System.out.println("Default Admin user created");
            }
        };
    }
}
