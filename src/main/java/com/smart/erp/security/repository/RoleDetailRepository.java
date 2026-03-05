package com.smart.erp.security.repository;

import com.smart.erp.security.model.RoleDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleDetailRepository extends JpaRepository<RoleDetail, Long> {

    @Query(value = "SELECT * FROM FND_ROLE_DETAILS_V WHERE ROLE_ID = :roleId", nativeQuery = true)
    List<RoleDetail> findByRoleId(@Param("roleId") Long roleId);

    @Query(value = "SELECT * FROM FND_ROLE_DETAILS_V WHERE MENU_DETAIL_ID = :menuDetailId", nativeQuery = true)
    List<RoleDetail> findByMenuDetailId(@Param("menuDetailId") Long menuDetailId);
}