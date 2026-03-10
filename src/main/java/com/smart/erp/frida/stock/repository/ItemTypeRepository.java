package com.smart.erp.frida.stock.repository;

import com.smart.erp.frida.stock.entity.ItemType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemTypeRepository extends JpaRepository<ItemType, Integer> {

    boolean existsByItemTypeNameEnIgnoreCase(String itemTypeNameEn);

    List<ItemType> findAllByOrderByItemTypeCodeAsc();
}
