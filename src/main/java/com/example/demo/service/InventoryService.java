package com.example.demo.service;

import com.example.demo.dto.InventoryItemDto;
import com.example.demo.dto.InventoryItemFullDto;
import com.example.demo.mapper.InventoryMapper;
import com.example.demo.model.appuser.AppUser;
import com.example.demo.model.item.Inventory;
import com.example.demo.model.item.Item;
import com.example.demo.repository.AppUserRepository;
import com.example.demo.repository.InventoryRepository;
import com.example.demo.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final AppUserRepository appUserRepository;
    private final InventoryRepository inventoryRepository;
    private final ItemRepository itemRepository;
    private final InventoryMapper mapper;

    public void checkItem(String username, InventoryItemDto inventoryItemDto) {
        AppUser user = appUserRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("qwe"));
        Item item = itemRepository.findByBarCodeNumber(inventoryItemDto.getBarCode()).orElseThrow(() -> new NotFoundException("Item with barcode " + inventoryItemDto.getBarCode() + " not found"));
        Inventory inventory = mapper.map(inventoryItemDto, user, item);
        inventoryRepository.save(inventory);
    }

    public List<InventoryItemFullDto> getInventoryDtoPageableItems(int size, int page) {
        List<Inventory> inventories = getInventoriesPageableItems(size, page);
        return mapper.mapToList(inventories);
    }

    private List<Inventory> getInventoriesPageableItems(int size, int page) {
        return inventoryRepository.findAll(PageRequest.of(page, size))
                .stream().collect(Collectors.toList());
    }

    public Long countInventory() {
        return inventoryRepository.count();
    }
}
