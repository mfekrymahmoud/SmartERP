package com.smart.erp.frida.stock.service.impl;

import com.smart.erp.frida.stock.entity.ItemUnit;
import com.smart.erp.frida.stock.exception.DuplicateResourceException;
import com.smart.erp.frida.stock.exception.ResourceNotFoundException;
import com.smart.erp.frida.stock.repository.ItemUnitRepository;
import com.smart.erp.frida.stock.service.ItemUnitService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class ItemUnitServiceImpl implements ItemUnitService {

    private final ItemUnitRepository itemUnitRepository;

    @Override
    public List<ItemUnit> findAll() {
        log.debug("Fetching all item units");
        return itemUnitRepository.findAllByOrderByUnitCodeAsc();
    }

    @Override
    public ItemUnit findById(Integer unitCode) {
        log.debug("Fetching item unit with code: {}", unitCode);
        return itemUnitRepository.findById(unitCode)
                .orElseThrow(() -> new ResourceNotFoundException("ItemUnit", "unitCode", unitCode));
    }

    @Override
    @Transactional
    public ItemUnit create(ItemUnit itemUnit) {
        log.debug("Creating item unit: {}", itemUnit.getUnitNameEn());
        if (itemUnitRepository.existsById(itemUnit.getUnitCode())) {
            throw new DuplicateResourceException("ItemUnit", "unitCode", itemUnit.getUnitCode());
        }
        if (itemUnitRepository.existsByUnitNameEnIgnoreCase(itemUnit.getUnitNameEn())) {
            throw new DuplicateResourceException("ItemUnit", "unitNameEn", itemUnit.getUnitNameEn());
        }
        ItemUnit saved = itemUnitRepository.save(itemUnit);
        log.info("Created item unit with code: {}", saved.getUnitCode());
        return saved;
    }

    @Override
    @Transactional
    public ItemUnit update(Integer unitCode, ItemUnit itemUnit) {
        log.debug("Updating item unit with code: {}", unitCode);
        ItemUnit existing = findById(unitCode);
        existing.setUnitNameEn(itemUnit.getUnitNameEn());
        existing.setUnitNameAr(itemUnit.getUnitNameAr());
        ItemUnit updated = itemUnitRepository.save(existing);
        log.info("Updated item unit with code: {}", unitCode);
        return updated;
    }

    @Override
    @Transactional
    public void delete(Integer unitCode) {
        log.debug("Deleting item unit with code: {}", unitCode);
        ItemUnit existing = findById(unitCode);
        itemUnitRepository.delete(existing);
        log.info("Deleted item unit with code: {}", unitCode);
    }
}
