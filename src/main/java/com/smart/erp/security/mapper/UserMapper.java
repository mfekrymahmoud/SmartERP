package com.smart.erp.security.mapper;

import com.smart.erp.security.dto.request.UserRequest;
import com.smart.erp.security.dto.response.UserResponse;
import com.smart.erp.security.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy; // Add this

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE) // Add policy here
public interface UserMapper {

    @Mapping(target = "isActive", expression = "java(user.isActive() ? \"Y\" : \"N\")")
    UserResponse toResponse(User user);

    @Mapping(target = "encryptedPassword", ignore = true)
    User toEntity(UserRequest request);

    @Mapping(target = "encryptedPassword", ignore = true)
    void updateEntityFromRequest(UserRequest request, @MappingTarget User user);
}