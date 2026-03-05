package com.smart.erp.security.repository;

import com.smart.erp.security.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    @Query(value = "SELECT * FROM FND_ROLES_V WHERE ROLE_ID = :roleId", nativeQuery = true)
    Optional<Role> findByIdView(@Param("roleId") Long roleId);

    @Query(value = "SELECT * FROM FND_ROLES_V WHERE IS_ACTIVE = 'Y'", nativeQuery = true)
    List<Role> findAllActive();

    @Query(value = "SELECT * FROM FND_ROLES_V", nativeQuery = true)
    List<Role> findAllView();

    @Query(value = "SELECT * FROM FND_ROLES_V WHERE ROLE_DESCRIPTION_EN = :desc OR ROLE_DESCRIPTION_AR = :desc", nativeQuery = true)
    List<Role> findByDescription(@Param("desc") String description);
}