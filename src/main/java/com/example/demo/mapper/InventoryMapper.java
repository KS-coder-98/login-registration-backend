package com.example.demo.mapper;

import com.example.demo.dto.InventoryItemDto;
import com.example.demo.dto.InventoryItemFullDto;
import com.example.demo.model.appuser.AppUser;
import com.example.demo.model.item.Inventory;
import com.example.demo.model.item.Item;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDateTime;
import java.util.List;

@Mapper(componentModel = "spring", imports = LocalDateTime.class)
public interface InventoryMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "description", source = "inventoryItemDto.description")
    @Mapping(target = "dataOfInventory", expression = "java(LocalDateTime.now())")
    Inventory map(InventoryItemDto inventoryItemDto, AppUser user, Item item);


    @Mapping(target = "username", source = "inventory.user.username")
    @Mapping(target = "itemName", source = "inventory.item.name")
    @Mapping(target = "barCode", source = "inventory.item.barCodeNumber")
    InventoryItemFullDto map(Inventory inventory);

    List<InventoryItemFullDto> mapToList(List<Inventory> inventories);
}
