package com.smart.erp.frida.stock.service;

import com.smart.erp.frida.stock.entity.ItemType;

import java.util.List;

public interface ItemTypeService {

    List<ItemType> findAll();

    ItemType findById(Integer itemTypeCode);

    ItemType create(ItemType itemType);

    ItemType update(Integer itemTypeCode, ItemType itemType);

    void delete(Integer itemTypeCode);
}
