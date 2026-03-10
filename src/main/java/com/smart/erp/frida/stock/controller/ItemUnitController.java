package com.smart.erp.frida.stock.controller;

import com.smart.erp.frida.stock.entity.ItemUnit;
import com.smart.erp.frida.stock.service.ItemUnitService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stock/item-units")
@RequiredArgsConstructor
@Tag(name = "Item Units", description = "CRUD operations for Item Units (FSTK_ITEM_UNITS)")
public class ItemUnitController {

    private final ItemUnitService itemUnitService;

    @GetMapping
    @Operation(summary = "Get all item units")
    public ResponseEntity<List<ItemUnit>> findAll() {
        return ResponseEntity.ok(itemUnitService.findAll());
    }

    @GetMapping("/{unitCode}")
    @Operation(summary = "Get item unit by code")
    public ResponseEntity<ItemUnit> findById(@PathVariable Integer unitCode) {
        return ResponseEntity.ok(itemUnitService.findById(unitCode));
    }

    @PostMapping
    @Operation(summary = "Create a new item unit")
    public ResponseEntity<ItemUnit> create(@Valid @RequestBody ItemUnit itemUnit) {
        return ResponseEntity.status(HttpStatus.CREATED).body(itemUnitService.create(itemUnit));
    }

    @PutMapping("/{unitCode}")
    @Operation(summary = "Update an existing item unit")
    public ResponseEntity<ItemUnit> update(
            @PathVariable Integer unitCode,
            @Valid @RequestBody ItemUnit itemUnit) {
        return ResponseEntity.ok(itemUnitService.update(unitCode, itemUnit));
    }

    @DeleteMapping("/{unitCode}")
    @Operation(summary = "Delete an item unit")
    public ResponseEntity<Void> delete(@PathVariable Integer unitCode) {
        itemUnitService.delete(unitCode);
        return ResponseEntity.noContent().build();
    }
}
