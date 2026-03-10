package com.smart.erp.frida.stock.service;

import com.smart.erp.frida.stock.dto.ItemRequestDto;
import com.smart.erp.frida.stock.dto.ItemResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ItemService {

    Page<ItemResponseDto> findAll(Pageable pageable);

    ItemResponseDto findById(Integer itemNumber);

    ItemResponseDto create(ItemRequestDto requestDto);

    ItemResponseDto update(Integer itemNumber, ItemRequestDto requestDto);

    void delete(Integer itemNumber);

    Page<ItemResponseDto> searchByKeyword(String keyword, Pageable pageable);

    Page<ItemResponseDto> findByItemType(Integer itemTypeCode, Pageable pageable);

    Page<ItemResponseDto> findByUnit(Integer unitCode, Pageable pageable);
}
