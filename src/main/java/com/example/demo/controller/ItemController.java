package com.example.demo.controller;

import com.example.demo.dto.ItemDto;
import com.example.demo.model.item.FixedAssetClassification;
import com.example.demo.model.item.Item;
import com.example.demo.service.ItemService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "api/v1/item")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @PostMapping
    @Operation(summary = "Create item")
    public ResponseEntity<ItemDto> createItem(@RequestBody ItemDto itemDto) {
        return ResponseEntity.ok(itemService.saveAndReturnDto(itemDto));
    }

    @GetMapping
    @Operation(summary = "Get item by id")
    public ResponseEntity<ItemDto> getItem(@RequestParam Long id) {
        return ResponseEntity.ok(itemService.getItemDtoById(id));
    }

    @GetMapping(path = "bar_code")
    @Operation(summary = "Get item by bar code")
    public ResponseEntity<ItemDto> getItem(@RequestParam String barCode) {
        return ResponseEntity.ok(itemService.getItemDtoByBarCode(barCode));
    }

    @GetMapping(path = "all", params = {"page", "size"})
    public ResponseEntity<List<Item>> getItems(@RequestParam("page") int page,
                                               @RequestParam("size") int size) {
        return ResponseEntity.ok(itemService.getAllItems());
        //todo need work its fake
    }


    @GetMapping(path = "classification_item")
    @Operation(summary = "Get all category of item",
            description = "Use this, for example, when you want to create a new object and in the form you will have to choose an enum")
    public ResponseEntity<Map<String, String>> getClassifications() {
        return ResponseEntity.ok().body(FixedAssetClassification.getAll());
    }
}
