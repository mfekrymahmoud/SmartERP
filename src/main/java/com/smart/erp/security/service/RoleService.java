package com.smart.erp.security.service;

import com.smart.erp.security.dto.request.RoleRequest;
import com.smart.erp.security.dto.response.RoleResponse;
import com.smart.erp.security.model.Role;
import com.smart.erp.security.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    @Transactional(readOnly = true)
    public List<RoleResponse> getAllRoles() {
        return roleRepository.findAllView().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public RoleResponse getRoleById(Long id) {
        Role role = roleRepository.findByIdView(id)
                .orElseThrow(() -> new RuntimeException("Role not found"));
        return mapToResponse(role);
    }

    @Transactional
    public RoleResponse createRole(RoleRequest request) {
        Role role = new Role();
        role.setRoleDescriptionAr(request.getRoleDescriptionAr());
        role.setRoleDescriptionEn(request.getRoleDescriptionEn());
        role.setEffectiveStartDate(request.getEffectiveStartDate());
        role.setEffectiveEndDate(request.getEffectiveEndDate());

        Role saved = roleRepository.save(role);
        return mapToResponse(saved);
    }

    @Transactional
    public RoleResponse updateRole(Long id, RoleRequest request) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Role not found"));

        role.setRoleDescriptionAr(request.getRoleDescriptionAr());
        role.setRoleDescriptionEn(request.getRoleDescriptionEn());
        role.setEffectiveStartDate(request.getEffectiveStartDate());
        role.setEffectiveEndDate(request.getEffectiveEndDate());

        Role saved = roleRepository.save(role);
        return mapToResponse(saved);
    }

    private RoleResponse mapToResponse(Role role) {
        RoleResponse response = new RoleResponse();
        response.setRoleId(role.getRoleId());
        response.setRoleDescriptionAr(role.getRoleDescriptionAr());
        response.setRoleDescriptionEn(role.getRoleDescriptionEn());
        response.setEffectiveStartDate(role.getEffectiveStartDate());
        response.setEffectiveEndDate(role.getEffectiveEndDate());
        response.setIsActive(role.isActive() ? "Y" : "N");
        response.setCreatedBy(role.getCreatedBy());
        response.setCreationDate(role.getCreationDate());
        response.setLastUpdatedBy(role.getLastUpdatedBy());
        response.setLastUpdateDate(role.getLastUpdateDate());
        return response;
    }
}