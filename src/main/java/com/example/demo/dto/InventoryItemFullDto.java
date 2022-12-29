package com.example.demo.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class InventoryItemFullDto extends InventoryItemDto {

    String username;
    String itemName;
    LocalDateTime dataOfInventory;

}
