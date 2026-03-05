package com.smart.erp.security.mapper;

import com.smart.erp.security.dto.request.UserRequest;
import com.smart.erp.security.dto.response.UserResponse;
import com.smart.erp.security.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {

    // Converts Entity to Response DTO
    @Mapping(target = "isActive", expression = "java(user.isActive() ? \"Y\" : \"N\")")
    UserResponse toResponse(User user);

    // Converts Request DTO to Entity (Ignoring password for manual encoding)
    @Mapping(target = "encryptedPassword", ignore = true)
    User toEntity(UserRequest request);

    // Updates existing Entity from Request
    @Mapping(target = "encryptedPassword", ignore = true)
    void updateEntityFromRequest(UserRequest request, @MappingTarget User user);
}