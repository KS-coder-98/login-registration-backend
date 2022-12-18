package com.example.demo.repository;

import com.example.demo.model.item.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface ItemRepository extends JpaRepository<Item, Long> {

    Optional<Item> findByBarCodeNumber(String barCode);

    long count();

    @Query(
            value = "SELECT bar_code_number FROM item",
            nativeQuery = true)
    List<String> getAllBarCode();
}
