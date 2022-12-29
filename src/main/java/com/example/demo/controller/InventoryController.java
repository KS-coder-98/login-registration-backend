package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.service.InventoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @PostMapping()
    public ResponseEntity<String> checkItem(HttpServletRequest request, @RequestBody InventoryItemDto inventoryItemDto) {
        Principal userPrincipal = request.getUserPrincipal();
        inventoryService.checkItem(userPrincipal.getName(), inventoryItemDto);
        return ResponseEntity.ok("Inventory successfully");
    }

    @GetMapping(path = "all", params = {"page", "size"})
    @Operation(summary = "Get all inventory - pagination")
    public ResponseEntity<InventoryDtoPageable> getItems(
            @Parameter(description = "Number of page") @RequestParam(value = "page", defaultValue = "0") int page,
            @Parameter(description = "Size of page") @RequestParam(value = "size", defaultValue = "10") int size) {
        List<InventoryItemFullDto> inventoryDtoPageableItems = inventoryService.getInventoryDtoPageableItems(size, page);
        Long countItems = inventoryService.countInventory();
        InventoryDtoPageable itemDtoPageable = new InventoryDtoPageable(inventoryDtoPageableItems, countItems);
        return ResponseEntity.ok(itemDtoPageable);
    }
}
