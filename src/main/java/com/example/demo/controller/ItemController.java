package com.example.demo.controller;

import com.example.demo.dto.ItemDto;
import com.example.demo.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/item")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @PostMapping
    public ResponseEntity<ItemDto> createItem(@RequestBody ItemDto itemDto) {
        return ResponseEntity.ok(itemService.saveAndReturnDto(itemDto));
    }

    @GetMapping
    public ResponseEntity<ItemDto> getItem(@RequestParam Long id) {
        return ResponseEntity.ok(itemService.getItemDtoById(id));
    }

    @GetMapping(path = "bar_code")
    public ResponseEntity<ItemDto> getItem(@RequestParam String barCode) {
        return ResponseEntity.ok(itemService.getItemDtoByBarCode(barCode));
    }
}
