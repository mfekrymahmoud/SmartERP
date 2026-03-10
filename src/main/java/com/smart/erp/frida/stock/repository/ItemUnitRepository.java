package com.smart.erp.frida.stock.repository;

import com.smart.erp.frida.stock.entity.ItemUnit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemUnitRepository extends JpaRepository<ItemUnit, Integer> {

    boolean existsByUnitNameEnIgnoreCase(String unitNameEn);

    List<ItemUnit> findAllByOrderByUnitCodeAsc();
}
