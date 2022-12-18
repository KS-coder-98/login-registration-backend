package com.example.demo.model.item;

import com.example.demo.model.appuser.AppUser;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
public class Item {

    @SequenceGenerator(
            name = "item_sequence",
            sequenceName = "item_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "item_sequence"
    )
    private Long id;

    private String name;
    private LocalDateTime purchaseDate; //data zakupu
    private String faxNumber;
    private Double purchasePrice; //cena zakupu
    private Double amountOfAnnualDepreciation; //kwota rocznej amortyzacji
    @Transient
    private Double currencyValue;
    private String description;
    private String location;
    private FixedAssetClassification classification;
    private String barCodeNumber;


    public Item(String name, LocalDateTime purchaseDate, String faxNumber, Double purchasePrice, Double amountOfAnnualDepreciation, String description, String location, FixedAssetClassification classification, String barCodeNumber) {
        this.name = name;
        this.purchaseDate = purchaseDate;
        this.faxNumber = faxNumber;
        this.purchasePrice = purchasePrice;
        this.amountOfAnnualDepreciation = amountOfAnnualDepreciation;
        this.description = description;
        this.location = location;
        this.classification = classification;
        this.barCodeNumber = barCodeNumber;
    }
}
