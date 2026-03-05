package com.smart.erp.security.repository;

import com.smart.erp.security.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {

    @Query(value = "SELECT * FROM FND_MENUS_V WHERE MENU_ID = :menuId", nativeQuery = true)
    Optional<Menu> findByIdView(@Param("menuId") Long menuId);

    @Query(value = "SELECT * FROM FND_MENUS_V", nativeQuery = true)
    List<Menu> findAllView();

    @Query(value = "SELECT * FROM FND_MENUS_V WHERE PARENT_MENU_ID = :parentId", nativeQuery = true)
    List<Menu> findByParentMenuId(@Param("parentId") Long parentId);

    @Query(value = "SELECT * FROM FND_MENUS_V WHERE MENU_CODE = :menuCode", nativeQuery = true)
    Optional<Menu> findByMenuCode(@Param("menuCode") String menuCode);
}