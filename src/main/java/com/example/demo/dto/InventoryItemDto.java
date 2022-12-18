package com.example.demo.dto;

import lombok.Data;

@Data
public class InventoryItemDto {

    String barCode;
    Boolean isOk;
    String description;
}
