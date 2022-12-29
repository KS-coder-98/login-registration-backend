package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class InventoryDtoPageable {

    List<InventoryItemFullDto> itemDtoList;
    Long countItems;
}
