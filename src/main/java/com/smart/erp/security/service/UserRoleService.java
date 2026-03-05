package com.smart.erp.security.service;

import com.smart.erp.security.dto.request.UserRoleRequest;
import com.smart.erp.security.dto.response.UserRoleResponse;
import com.smart.erp.security.model.UserRole;
import com.smart.erp.security.repository.UserRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserRoleService {

    private final UserRoleRepository userRoleRepository;

    @Transactional(readOnly = true)
    public List<UserRoleResponse> getAllUserRoles() {
        return userRoleRepository.findAllView().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<UserRoleResponse> getUserRolesByUserId(Long userId) {
        return userRoleRepository.findByUserId(userId).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public UserRoleResponse getUserRoleById(Long id) {
        UserRole userRole = userRoleRepository.findByIdView(id)
                .orElseThrow(() -> new RuntimeException("User role not found"));
        return mapToResponse(userRole);
    }

    @Transactional
    public UserRoleResponse createUserRole(UserRoleRequest request) {
        UserRole userRole = new UserRole();
        userRole.setUserId(request.getUserId());
        userRole.setRoleId(request.getRoleId());
        userRole.setEffectiveStartDate(request.getEffectiveStartDate());
        userRole.setEffectiveEndDate(request.getEffectiveEndDate());

        UserRole saved = userRoleRepository.save(userRole);
        return mapToResponse(saved);
    }

    @Transactional
    public UserRoleResponse updateUserRole(Long id, UserRoleRequest request) {
        UserRole userRole = userRoleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User role not found"));

        userRole.setUserId(request.getUserId());
        userRole.setRoleId(request.getRoleId());
        userRole.setEffectiveStartDate(request.getEffectiveStartDate());
        userRole.setEffectiveEndDate(request.getEffectiveEndDate());

        UserRole saved = userRoleRepository.save(userRole);
        return mapToResponse(saved);
    }

    private UserRoleResponse mapToResponse(UserRole userRole) {
        UserRoleResponse response = new UserRoleResponse();
        response.setUserRoleId(userRole.getUserRoleId());
        response.setUserId(userRole.getUserId());
        response.setRoleId(userRole.getRoleId());
        response.setEffectiveStartDate(userRole.getEffectiveStartDate());
        response.setEffectiveEndDate(userRole.getEffectiveEndDate());
        response.setIsActive(userRole.isActive() ? "Y" : "N");
        response.setCreatedBy(userRole.getCreatedBy());
        response.setCreationDate(userRole.getCreationDate());
        response.setLastUpdatedBy(userRole.getLastUpdatedBy());
        response.setLastUpdateDate(userRole.getLastUpdateDate());
        return response;
    }
}