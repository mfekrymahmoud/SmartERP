package com.smart.erp.security.repository;

import com.smart.erp.security.model.MenuDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MenuDetailRepository extends JpaRepository<MenuDetail, Long> {

    @Query(value = "SELECT * FROM FND_MENU_DETAILS_V WHERE MENU_DETAIL_ID = :menuDetailId", nativeQuery = true)
    Optional<MenuDetail> findByIdView(@Param("menuDetailId") Long menuDetailId);

    @Query(value = "SELECT * FROM FND_MENU_DETAILS_V", nativeQuery = true)
    List<MenuDetail> findAllView();

    @Query(value = "SELECT * FROM FND_MENU_DETAILS_V WHERE MENU_ID = :menuId", nativeQuery = true)
    List<MenuDetail> findByMenuId(@Param("menuId") Long menuId);

    @Query(value = "SELECT * FROM FND_MENU_DETAILS_V WHERE FUNCTION_ID = :functionId", nativeQuery = true)
    List<MenuDetail> findByFunctionId(@Param("functionId") Long functionId);
}