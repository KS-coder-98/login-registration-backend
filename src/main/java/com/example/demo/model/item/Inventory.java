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
public class Inventory {

    @SequenceGenerator(
            name = "inventory_sequence",
            sequenceName = "inventory_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "inventory_sequence"
    )
    private Long id;

    @ManyToOne
    @JoinColumn(
            nullable = false,
            name = "item_id"
    )
    private Item item;

    private Boolean isOk;
    private String description;
    private LocalDateTime dataOfInventory;

    @ManyToOne
    @JoinColumn(
            nullable = false,
            name = "user_id"
    )
    private AppUser user;

    public Inventory(Item item, Boolean isOk, String description, LocalDateTime dataOfInventory, AppUser user) {
        this.item = item;
        this.isOk = isOk;
        this.description = description;
        this.dataOfInventory = dataOfInventory;
        this.user = user;
    }
}
