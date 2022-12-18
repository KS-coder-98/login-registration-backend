package com.example.demo.mapper;

import com.example.demo.dto.ItemDto;
import com.example.demo.model.item.Item;
import org.mapstruct.BeforeMapping;
import org.mapstruct.Mapper;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ItemMapper {

    Item map(ItemDto itemDto);

    ItemDto map(Item item);

    List<ItemDto> mapToList(List<Item> itemList);

    @BeforeMapping
    default void calcCurrentValue(Item item) {
        double amountOfMonthDepreciation = item.getAmountOfAnnualDepreciation() / 12;
        long betweenPeriodInMonth = ChronoUnit.MONTHS.between(item.getPurchaseDate(), LocalDateTime.now());
        double result = betweenPeriodInMonth * amountOfMonthDepreciation;
        double currentValue = item.getPurchasePrice() - result > 0 ? item.getPurchasePrice() - result : 0;
        item.setCurrencyValue(currentValue);
    }
}
