package com.project.user_service.service;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.user_service.dto.LoginRequestDTO;
import com.project.user_service.dto.UserRequestDTO;
import com.project.user_service.dto.UserResponseDTO;
import com.project.user_service.entity.User;
import com.project.user_service.mapper.UserMapper;
import com.project.user_service.repository.UserRepository;
import com.project.user_service.util.JwtUtil;



@Service
public class UserService {

    private final UserRepository repo;
    private final UserMapper mapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public UserService(UserRepository repo,
                       UserMapper mapper,
                       PasswordEncoder passwordEncoder,
                       JwtUtil jwtUtil) {
        this.repo = repo;
        this.mapper = mapper;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }
    
    public UserResponseDTO register(UserRequestDTO dto) {

        if (repo.findByEmail(dto.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }

        User user = mapper.toEntity(dto);

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        user.setRole("USER");   // 🔥🔥 MUST ADD

        return mapper.toDTO(repo.save(user));
    }
  
    public String login(LoginRequestDTO dto) {

        User user = repo.findByEmail(dto.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        return jwtUtil.generateToken(user.getEmail(), user.getRole());
    }
}