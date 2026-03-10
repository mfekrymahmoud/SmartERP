package com.smart.erp.frida.stock.controller;

import com.smart.erp.frida.stock.dto.ItemRequestDto;
import com.smart.erp.frida.stock.dto.ItemResponseDto;
import com.smart.erp.frida.stock.service.ItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/stock/items")
@RequiredArgsConstructor
@Tag(name = "Items", description = "CRUD operations for Items (FSTK_ITEMS)")
public class ItemController {

    private final ItemService itemService;

    @GetMapping
    @Operation(summary = "Get all items (paginated)")
    public ResponseEntity<Page<ItemResponseDto>> findAll(
            @PageableDefault(size = 20, sort = "itemNumber") Pageable pageable) {
        return ResponseEntity.ok(itemService.findAll(pageable));
    }

    @GetMapping("/{itemNumber}")
    @Operation(summary = "Get item by number")
    public ResponseEntity<ItemResponseDto> findById(@PathVariable Integer itemNumber) {
        return ResponseEntity.ok(itemService.findById(itemNumber));
    }

    @GetMapping("/search")
    @Operation(summary = "Search items by keyword (EN or AR name)")
    public ResponseEntity<Page<ItemResponseDto>> search(
            @Parameter(description = "Keyword to search in item names")
            @RequestParam(required = false) String keyword,
            @PageableDefault(size = 20) Pageable pageable) {
        return ResponseEntity.ok(itemService.searchByKeyword(keyword, pageable));
    }

    @GetMapping("/by-type/{itemTypeCode}")
    @Operation(summary = "Get items filtered by item type")
    public ResponseEntity<Page<ItemResponseDto>> findByItemType(
            @PathVariable Integer itemTypeCode,
            @PageableDefault(size = 20) Pageable pageable) {
        return ResponseEntity.ok(itemService.findByItemType(itemTypeCode, pageable));
    }

    @GetMapping("/by-unit/{unitCode}")
    @Operation(summary = "Get items filtered by unit")
    public ResponseEntity<Page<ItemResponseDto>> findByUnit(
            @PathVariable Integer unitCode,
            @PageableDefault(size = 20) Pageable pageable) {
        return ResponseEntity.ok(itemService.findByUnit(unitCode, pageable));
    }

    @PostMapping
    @Operation(summary = "Create a new item")
    public ResponseEntity<ItemResponseDto> create(@Valid @RequestBody ItemRequestDto requestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(itemService.create(requestDto));
    }

    @PutMapping("/{itemNumber}")
    @Operation(summary = "Update an existing item")
    public ResponseEntity<ItemResponseDto> update(
            @PathVariable Integer itemNumber,
            @Valid @RequestBody ItemRequestDto requestDto) {
        return ResponseEntity.ok(itemService.update(itemNumber, requestDto));
    }

    @DeleteMapping("/{itemNumber}")
    @Operation(summary = "Delete an item")
    public ResponseEntity<Void> delete(@PathVariable Integer itemNumber) {
        itemService.delete(itemNumber);
        return ResponseEntity.noContent().build();
    }
}
