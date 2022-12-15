package com.example.demo.dto;

import com.example.demo.model.item.FixedAssetClassification;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ItemDto {

    private Long id;
    private String name;
    private LocalDateTime purchaseDate;
    private String faxNumber;
    private Double purchasePrice;
    private Double amountOfAnnualDepreciation;
    private String description;
    private String location;
    private FixedAssetClassification classification;
    private String barCodeNumber;
}
