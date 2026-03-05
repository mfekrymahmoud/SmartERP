package com.smart.erp.security.repository;

import com.smart.erp.security.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

    @Query(value = "SELECT * FROM FND_USER_ROLES_V WHERE USER_ROLE_ID = :userRoleId", nativeQuery = true)
    Optional<UserRole> findByIdView(@Param("userRoleId") Long userRoleId);

    @Query(value = "SELECT * FROM FND_USER_ROLES_V WHERE USER_ID = :userId", nativeQuery = true)
    List<UserRole> findByUserId(@Param("userId") Long userId);

    @Query(value = "SELECT * FROM FND_USER_ROLES_V WHERE ROLE_ID = :roleId", nativeQuery = true)
    List<UserRole> findByRoleId(@Param("roleId") Long roleId);

    @Query(value = "SELECT * FROM FND_USER_ROLES_V", nativeQuery = true)
    List<UserRole> findAllView();

    @Query(value = "SELECT * FROM FND_USER_ROLES_V WHERE IS_ACTIVE = 'Y' AND USER_ID = :userId", nativeQuery = true)
    List<UserRole> findActiveByUserId(@Param("userId") Long userId);
}