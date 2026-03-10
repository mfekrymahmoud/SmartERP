package com.smart.erp.frida.stock.mapper;

import com.smart.erp.frida.stock.dto.ItemRequestDto;
import com.smart.erp.frida.stock.dto.ItemResponseDto;
import com.smart.erp.frida.stock.entity.Item;
import com.smart.erp.frida.stock.entity.ItemType;
import com.smart.erp.frida.stock.entity.ItemUnit;
import org.springframework.stereotype.Component;

@Component
public class ItemMapper {

    /** Map request DTO → new entity (lookups resolved by service layer) */
    public Item toEntity(ItemRequestDto dto, ItemUnit unit, ItemType itemType) {
        return Item.builder()
                .itemNumber(dto.getItemNumber())
                .itemNameEn(dto.getItemNameEn())
                .itemNameAr(dto.getItemNameAr())
                .unit(unit)
                .itemType(itemType)
                .itemPrice(dto.getItemPrice())
                .robotNozzleCode(dto.getRobotNozzleCode())
                .build();
    }

    /** Apply request DTO fields onto an existing entity (for update) */
    public void updateEntity(Item item, ItemRequestDto dto, ItemUnit unit, ItemType itemType) {
        item.setItemNameEn(dto.getItemNameEn());
        item.setItemNameAr(dto.getItemNameAr());
        item.setUnit(unit);
        item.setItemType(itemType);
        item.setItemPrice(dto.getItemPrice());
        item.setRobotNozzleCode(dto.getRobotNozzleCode());
    }

    /** Map entity → response DTO */
    public ItemResponseDto toDto(Item item) {
        return ItemResponseDto.builder()
                .itemNumber(item.getItemNumber())
                .itemNameEn(item.getItemNameEn())
                .itemNameAr(item.getItemNameAr())
                .unitCode(item.getUnit().getUnitCode())
                .unitNameEn(item.getUnit().getUnitNameEn())
                .itemTypeCode(item.getItemType().getItemTypeCode())
                .itemTypeNameEn(item.getItemType().getItemTypeNameEn())
                .itemPrice(item.getItemPrice())
                .robotNozzleCode(item.getRobotNozzleCode())
                .creationBy(item.getCreationBy())
                .creationDate(item.getCreationDate())
                .lastUpdateBy(item.getLastUpdateBy())
                .lastUpdateDate(item.getLastUpdateDate())
                .build();
    }
}
