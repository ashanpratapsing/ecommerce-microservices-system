package com.project.user_service.mapper;


import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.project.user_service.dto.UserRequestDTO;
import com.project.user_service.dto.UserResponseDTO;
import com.project.user_service.entity.User;



@Component
public class UserMapper {

    public User toEntity(UserRequestDTO dto) {
        User user = new User();
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setRole("USER");
        user.setCreatedAt(LocalDateTime.now());
        return user;
    }

    public UserResponseDTO toDTO(User user) {
        return new UserResponseDTO(
            user.getId(),
            user.getEmail(),
            user.getRole()
        );
    }
}
