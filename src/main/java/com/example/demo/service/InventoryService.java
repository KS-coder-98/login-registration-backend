package com.example.demo.service;

import com.example.demo.dto.InventoryItemDto;
import com.example.demo.mapper.InventoryMapper;
import com.example.demo.model.appuser.AppUser;
import com.example.demo.model.item.Inventory;
import com.example.demo.model.item.Item;
import com.example.demo.repository.AppUserRepository;
import com.example.demo.repository.InventoryRepository;
import com.example.demo.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final AppUserRepository appUserRepository;
    private final InventoryRepository inventoryRepository;
    private final ItemRepository itemRepository;
    private final InventoryMapper mapper;

    public Inventory checkItem(String username, InventoryItemDto inventoryItemDto) {
        AppUser user = appUserRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("qwe"));
        Item item = itemRepository.findByBarCodeNumber(inventoryItemDto.getBarCode()).orElseThrow(() -> new NotFoundException("Item with barcode " + inventoryItemDto.getBarCode() + " not found"));
        Inventory inventory = mapper.map(inventoryItemDto, user, item);
        return inventoryRepository.save(inventory);
    }
}
