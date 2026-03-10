package com.smart.erp.frida.stock.service;

import com.smart.erp.frida.stock.entity.ItemUnit;

import java.util.List;

public interface ItemUnitService {

    List<ItemUnit> findAll();

    ItemUnit findById(Integer unitCode);

    ItemUnit create(ItemUnit itemUnit);

    ItemUnit update(Integer unitCode, ItemUnit itemUnit);

    void delete(Integer unitCode);
}
