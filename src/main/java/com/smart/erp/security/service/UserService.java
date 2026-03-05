package com.smart.erp.security.service;

import com.smart.erp.security.dto.request.UserRequest;
import com.smart.erp.security.dto.response.UserResponse;
import com.smart.erp.security.mapper.UserMapper;
import com.smart.erp.security.model.User;
import com.smart.erp.security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper; // Injected MapStruct Mapper

    @Transactional(readOnly = true)
    public List<UserResponse> getAllUsers() {
        return userRepository.findAllView().stream()
                .map(userMapper::toResponse) // Uses MapStruct
                .collect(Collectors.toList());
    }

    @Transactional
    public UserResponse createUser(UserRequest request) {
        if (userRepository.existsByUserName(request.getUserName())) {
            throw new RuntimeException("Username already exists");
        }

        User user = userMapper.toEntity(request);
        user.setEncryptedPassword(passwordEncoder.encode(request.getPassword()));

        User saved = userRepository.save(user);
        return userMapper.toResponse(saved);
    }

    @Transactional
    public UserResponse updateUser(Long id, UserRequest request) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        userMapper.updateEntityFromRequest(request, user);

        if (request.getPassword() != null && !request.getPassword().isEmpty()) {
            user.setEncryptedPassword(passwordEncoder.encode(request.getPassword()));
        }

        return userMapper.toResponse(userRepository.save(user));
    }

    // UserService.java
    @Transactional(readOnly = true)
    public UserResponse getUserById(Long id) {
        // Advice: Use findByIdView to stay consistent with your native SQL/View pattern
        User user = userRepository.findByIdView(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        return userMapper.toResponse(user);
    }
}