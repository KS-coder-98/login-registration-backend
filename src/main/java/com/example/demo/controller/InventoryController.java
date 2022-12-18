package com.example.demo.controller;

import com.example.demo.dto.InventoryItemDto;
import com.example.demo.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

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
}
