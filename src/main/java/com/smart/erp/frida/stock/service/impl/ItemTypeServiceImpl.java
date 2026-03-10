package com.smart.erp.frida.stock.service.impl;

import com.smart.erp.frida.stock.entity.ItemType;
import com.smart.erp.frida.stock.exception.DuplicateResourceException;
import com.smart.erp.frida.stock.exception.ResourceNotFoundException;
import com.smart.erp.frida.stock.repository.ItemTypeRepository;
import com.smart.erp.frida.stock.service.ItemTypeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class ItemTypeServiceImpl implements ItemTypeService {

    private final ItemTypeRepository itemTypeRepository;

    @Override
    public List<ItemType> findAll() {
        log.debug("Fetching all item types");
        return itemTypeRepository.findAllByOrderByItemTypeCodeAsc();
    }

    @Override
    public ItemType findById(Integer itemTypeCode) {
        log.debug("Fetching item type with code: {}", itemTypeCode);
        return itemTypeRepository.findById(itemTypeCode)
                .orElseThrow(() -> new ResourceNotFoundException("ItemType", "itemTypeCode", itemTypeCode));
    }

    @Override
    @Transactional
    public ItemType create(ItemType itemType) {
        log.debug("Creating item type: {}", itemType.getItemTypeNameEn());
        if (itemTypeRepository.existsById(itemType.getItemTypeCode())) {
            throw new DuplicateResourceException("ItemType", "itemTypeCode", itemType.getItemTypeCode());
        }
        if (itemTypeRepository.existsByItemTypeNameEnIgnoreCase(itemType.getItemTypeNameEn())) {
            throw new DuplicateResourceException("ItemType", "itemTypeNameEn", itemType.getItemTypeNameEn());
        }
        ItemType saved = itemTypeRepository.save(itemType);
        log.info("Created item type with code: {}", saved.getItemTypeCode());
        return saved;
    }

    @Override
    @Transactional
    public ItemType update(Integer itemTypeCode, ItemType itemType) {
        log.debug("Updating item type with code: {}", itemTypeCode);
        ItemType existing = findById(itemTypeCode);
        existing.setItemTypeNameEn(itemType.getItemTypeNameEn());
        existing.setItemTypeNameAr(itemType.getItemTypeNameAr());
        ItemType updated = itemTypeRepository.save(existing);
        log.info("Updated item type with code: {}", itemTypeCode);
        return updated;
    }

    @Override
    @Transactional
    public void delete(Integer itemTypeCode) {
        log.debug("Deleting item type with code: {}", itemTypeCode);
        ItemType existing = findById(itemTypeCode);
        itemTypeRepository.delete(existing);
        log.info("Deleted item type with code: {}", itemTypeCode);
    }
}
