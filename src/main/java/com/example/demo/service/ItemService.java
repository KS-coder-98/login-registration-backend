package com.example.demo.service;

import com.example.demo.dto.ItemDto;
import com.example.demo.mapper.ItemMapper;
import com.example.demo.model.item.Item;
import com.example.demo.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;

    public Item save(ItemDto itemDto) {
        return itemRepository.save(itemMapper.map(itemDto));
    }

    public ItemDto saveAndReturnDto(ItemDto itemDto) {
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

    public List<Item> getAllItems(){
        return itemRepository.findAll();
    }
}
