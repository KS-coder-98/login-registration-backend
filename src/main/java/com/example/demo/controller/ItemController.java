package com.example.demo.controller;

import com.example.demo.dto.ItemDto;
import com.example.demo.dto.ItemDtoPageable;
import com.example.demo.model.item.FixedAssetClassification;
import com.example.demo.service.BarCodeService;
import com.example.demo.service.ItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.image.BufferedImage;
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

    @GetMapping(params = "id")
    @Operation(summary = "Get item by id")
    public ResponseEntity<ItemDto> getItem(@RequestParam("id") Long id) {
        return ResponseEntity.ok(itemService.getItemDtoById(id));
    }

    @GetMapping(path = "bar_code")
    @Operation(summary = "Get item by bar code")
    public ResponseEntity<ItemDto> getItem(@RequestParam String barCode) {
        return ResponseEntity.ok(itemService.getItemDtoByBarCode(barCode));
    }

    @GetMapping(value = "barbecue", produces = MediaType.IMAGE_PNG_VALUE, params = "barcode")
    @Operation(summary = "Generate bar code from string")
    public ResponseEntity<BufferedImage> barbecueEAN13Barcode(@RequestParam("barcode") String barcode)
            throws Exception {
        return ResponseEntity.ok (BarCodeService.generateEAN13BarcodeImage(barcode) );
    }

    @GetMapping(path = "all", params = {"page", "size"})
    @Operation(summary = "Get all items - pagination")
    public ResponseEntity<ItemDtoPageable> getItems(
            @Parameter(description = "Number of page") @RequestParam(value = "page", defaultValue = "0") int page,
            @Parameter(description = "Size of page") @RequestParam(value = "size", defaultValue = "10") int size) {
        List<ItemDto> itemsDtoPageableItems = itemService.getItemsDtoPageableItems(size, page);
        Long countItems = itemService.countItems();
        ItemDtoPageable itemDtoPageable = new ItemDtoPageable(itemsDtoPageableItems, countItems);
        return ResponseEntity.ok(itemDtoPageable);
    }


    @GetMapping(path = "classification_item")
    @Operation(summary = "Get all category of item",
            description = "Use this, for example, when you want to create a new object and in the form you will have to choose an enum")
    public ResponseEntity<Map<String, String>> getClassifications() {
        return ResponseEntity.ok().body(FixedAssetClassification.getAll());
    }
}
