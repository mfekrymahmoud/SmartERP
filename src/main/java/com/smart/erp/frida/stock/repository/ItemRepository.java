package com.smart.erp.frida.stock.repository;

import com.smart.erp.frida.stock.entity.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {

    boolean existsByItemNameEnIgnoreCase(String itemNameEn);

    Page<Item> findByItemType_ItemTypeCode(Integer itemTypeCode, Pageable pageable);

    Page<Item> findByUnit_UnitCode(Integer unitCode, Pageable pageable);

    @Query("""
            SELECT i FROM Item i
            WHERE (:keyword IS NULL
                   OR LOWER(i.itemNameEn) LIKE LOWER(CONCAT('%', :keyword, '%'))
                   OR LOWER(i.itemNameAr) LIKE LOWER(CONCAT('%', :keyword, '%')))
            """)
    Page<Item> searchByKeyword(@Param("keyword") String keyword, Pageable pageable);
}
