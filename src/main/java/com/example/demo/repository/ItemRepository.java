package com.example.demo.repository;

import com.example.demo.model.item.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface ItemRepository extends JpaRepository<Item, Long> {

    Optional<Item> findByBarCodeNumber(String barCode);
}
