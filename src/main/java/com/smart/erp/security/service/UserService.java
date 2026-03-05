package com.smart.erp.security.service;

import com.smart.erp.security.dto.request.UserRequest;
import com.smart.erp.security.dto.response.UserResponse;
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

    @Transactional(readOnly = true)
    public List<UserResponse> getAllUsers() {
        return userRepository.findAllView().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public UserResponse getUserById(Long id) {
        User user = userRepository.findByIdView(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return mapToResponse(user);
    }

    @Transactional
    public UserResponse createUser(UserRequest request) {
        if (userRepository.existsByUserName(request.getUserName())) {
            throw new RuntimeException("Username already exists");
        }

        User user = new User();
        user.setUserName(request.getUserName());
        user.setEncryptedPassword(passwordEncoder.encode(request.getPassword()));
        user.setExpirationMinutes(request.getExpirationMinutes());
        user.setEffectiveStartDate(request.getEffectiveStartDate());
        user.setEffectiveEndDate(request.getEffectiveEndDate());
        user.setLanguagePreference(request.getLanguagePreference());

        User saved = userRepository.save(user);
        return mapToResponse(saved);
    }

    @Transactional
    public UserResponse updateUser(Long id, UserRequest request) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setUserName(request.getUserName());
        if (request.getPassword() != null && !request.getPassword().isEmpty()) {
            user.setEncryptedPassword(passwordEncoder.encode(request.getPassword()));
        }
        user.setExpirationMinutes(request.getExpirationMinutes());
        user.setEffectiveStartDate(request.getEffectiveStartDate());
        user.setEffectiveEndDate(request.getEffectiveEndDate());
        user.setLanguagePreference(request.getLanguagePreference());

        User saved = userRepository.save(user);
        return mapToResponse(saved);
    }

    private UserResponse mapToResponse(User user) {
        UserResponse response = new UserResponse();
        response.setUserId(user.getUserId());
        response.setUserName(user.getUserName());
        response.setExpirationMinutes(user.getExpirationMinutes());
        response.setEffectiveStartDate(user.getEffectiveStartDate());
        response.setEffectiveEndDate(user.getEffectiveEndDate());
        response.setLanguagePreference(user.getLanguagePreference());
        response.setIsActive(user.isActive() ? "Y" : "N");
        response.setCreatedBy(user.getCreatedBy());
        response.setCreationDate(user.getCreationDate());
        response.setLastUpdatedBy(user.getLastUpdatedBy());
        response.setLastUpdateDate(user.getLastUpdateDate());
        return response;
    }
}