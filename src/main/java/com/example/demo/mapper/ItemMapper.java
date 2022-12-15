package com.example.demo.mapper;

import com.example.demo.dto.ItemDto;
import com.example.demo.model.item.Item;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ItemMapper {

    Item map(ItemDto itemDto);

    ItemDto map(Item item);
}
