package com.smart.erp.frida.stock.controller;

import com.smart.erp.frida.stock.entity.ItemType;
import com.smart.erp.frida.stock.service.ItemTypeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stock/item-types")
@RequiredArgsConstructor
@Tag(name = "Item Types", description = "CRUD operations for Item Types (FSTK_ITEM_TYPES)")
public class ItemTypeController {

    private final ItemTypeService itemTypeService;

    @GetMapping
    @Operation(summary = "Get all item types")
    public ResponseEntity<List<ItemType>> findAll() {
        return ResponseEntity.ok(itemTypeService.findAll());
    }

    @GetMapping("/{itemTypeCode}")
    @Operation(summary = "Get item type by code")
    public ResponseEntity<ItemType> findById(@PathVariable Integer itemTypeCode) {
        return ResponseEntity.ok(itemTypeService.findById(itemTypeCode));
    }

    @PostMapping
    @Operation(summary = "Create a new item type")
    public ResponseEntity<ItemType> create(@Valid @RequestBody ItemType itemType) {
        return ResponseEntity.status(HttpStatus.CREATED).body(itemTypeService.create(itemType));
    }

    @PutMapping("/{itemTypeCode}")
    @Operation(summary = "Update an existing item type")
    public ResponseEntity<ItemType> update(
            @PathVariable Integer itemTypeCode,
            @Valid @RequestBody ItemType itemType) {
        return ResponseEntity.ok(itemTypeService.update(itemTypeCode, itemType));
    }

    @DeleteMapping("/{itemTypeCode}")
    @Operation(summary = "Delete an item type")
    public ResponseEntity<Void> delete(@PathVariable Integer itemTypeCode) {
        itemTypeService.delete(itemTypeCode);
        return ResponseEntity.noContent().build();
    }
}
