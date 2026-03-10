package com.smart.erp.frida.stock.service.impl;

import com.smart.erp.frida.stock.dto.ItemRequestDto;
import com.smart.erp.frida.stock.dto.ItemResponseDto;
import com.smart.erp.frida.stock.entity.Item;
import com.smart.erp.frida.stock.entity.ItemType;
import com.smart.erp.frida.stock.entity.ItemUnit;
import com.smart.erp.frida.stock.exception.DuplicateResourceException;
import com.smart.erp.frida.stock.exception.ResourceNotFoundException;
import com.smart.erp.frida.stock.mapper.ItemMapper;
import com.smart.erp.frida.stock.repository.ItemRepository;
import com.smart.erp.frida.stock.service.ItemService;
import com.smart.erp.frida.stock.service.ItemTypeService;
import com.smart.erp.frida.stock.service.ItemUnitService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class ItemServiceImpl implements ItemService {

    private final ItemRepository     itemRepository;
    private final ItemUnitService    itemUnitService;
    private final ItemTypeService    itemTypeService;
    private final ItemMapper         itemMapper;

    @Override
    public Page<ItemResponseDto> findAll(Pageable pageable) {
        log.debug("Fetching all items, page: {}", pageable.getPageNumber());
        return itemRepository.findAll(pageable).map(itemMapper::toDto);
    }

    @Override
    public ItemResponseDto findById(Integer itemNumber) {
        log.debug("Fetching item with number: {}", itemNumber);
        return itemMapper.toDto(getItemOrThrow(itemNumber));
    }

    @Override
    @Transactional
    public ItemResponseDto create(ItemRequestDto dto) {
        log.debug("Creating item: {}", dto.getItemNameEn());

        if (itemRepository.existsById(dto.getItemNumber())) {
            throw new DuplicateResourceException("Item", "itemNumber", dto.getItemNumber());
        }
        if (itemRepository.existsByItemNameEnIgnoreCase(dto.getItemNameEn())) {
            throw new DuplicateResourceException("Item", "itemNameEn", dto.getItemNameEn());
        }

        ItemUnit unit     = itemUnitService.findById(dto.getUnitCode());
        ItemType itemType = itemTypeService.findById(dto.getItemTypeCode());

        Item saved = itemRepository.save(itemMapper.toEntity(dto, unit, itemType));
        log.info("Created item with number: {}", saved.getItemNumber());
        return itemMapper.toDto(saved);
    }

    @Override
    @Transactional
    public ItemResponseDto update(Integer itemNumber, ItemRequestDto dto) {
        log.debug("Updating item with number: {}", itemNumber);

        Item existing = getItemOrThrow(itemNumber);
        ItemUnit unit     = itemUnitService.findById(dto.getUnitCode());
        ItemType itemType = itemTypeService.findById(dto.getItemTypeCode());

        itemMapper.updateEntity(existing, dto, unit, itemType);
        Item updated = itemRepository.save(existing);
        log.info("Updated item with number: {}", itemNumber);
        return itemMapper.toDto(updated);
    }

    @Override
    @Transactional
    public void delete(Integer itemNumber) {
        log.debug("Deleting item with number: {}", itemNumber);
        Item existing = getItemOrThrow(itemNumber);
        itemRepository.delete(existing);
        log.info("Deleted item with number: {}", itemNumber);
    }

    @Override
    public Page<ItemResponseDto> searchByKeyword(String keyword, Pageable pageable) {
        return itemRepository.searchByKeyword(keyword, pageable).map(itemMapper::toDto);
    }

    @Override
    public Page<ItemResponseDto> findByItemType(Integer itemTypeCode, Pageable pageable) {
        return itemRepository.findByItemType_ItemTypeCode(itemTypeCode, pageable).map(itemMapper::toDto);
    }

    @Override
    public Page<ItemResponseDto> findByUnit(Integer unitCode, Pageable pageable) {
        return itemRepository.findByUnit_UnitCode(unitCode, pageable).map(itemMapper::toDto);
    }

    // ─── helpers ──────────────────────────────────────────────────────────────

    private Item getItemOrThrow(Integer itemNumber) {
        return itemRepository.findById(itemNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Item", "itemNumber", itemNumber));
    }
}
