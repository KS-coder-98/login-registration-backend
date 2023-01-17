package com.example.demo.service;

import com.example.demo.dto.ItemDto;
import com.example.demo.mapper.ItemMapper;
import com.example.demo.model.item.Item;
import com.example.demo.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import javax.persistence.Transient;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;

    public Item save(ItemDto itemDto) {
        return itemRepository.save(itemMapper.map(itemDto));
    }

    @Transient
    public ItemDto saveAndReturnDto(ItemDto itemDto) {
        List<String> allBarCode = itemRepository.getAllBarCode();
        String randomBarCode;
        do {
            Random random = new Random();
            randomBarCode = String.valueOf(random.ints(1000000, 9999999)
                    .findFirst().getAsInt());
        } while (allBarCode.contains(randomBarCode));
        itemDto.setBarCodeNumber(randomBarCode);
        return itemMapper.map(save(itemDto));
    }

    public Item getItemById(Long id) {
        return itemRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Not found item with id: " + id));
    }

    public ItemDto getItemDtoById(Long id) {
        return itemMapper.map(getItemById(id));
    }

    public ItemDto getItemDtoByBarCode(String barCode) {
        return itemMapper.map(itemRepository.findByBarCodeNumber(barCode)
                .orElseThrow(() -> new NotFoundException("Not found item with id: " + barCode)));
    }

    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    public List<Item> getItemsPageableItems(int size, int page, String sort) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        return itemRepository.findAll(pageable).getContent();
    }

    public List<ItemDto> getItemsDtoPageableItems(int size, int page, String sort) {
        List<Item> itemsPageableItems = getItemsPageableItems(size, page, sort);
        return itemMapper.mapToList(itemsPageableItems);
    }

    public Long countItems() {
        return itemRepository.count();
    }

    public ItemDto updateItem(Long id, ItemDto itemDto) {
        Item item = itemRepository.findById(id).orElseThrow(() -> new NotFoundException("Not found item with id: " + id));
        ItemDto map = itemMapper.map(item);
        itemMapper.updateItem(itemDto, map);
        return itemMapper.map(itemRepository.save(itemMapper.map(map)));
    }
}
