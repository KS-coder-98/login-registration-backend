package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/user")
public class InventoryController {

    @GetMapping(params = {"itemId", "isOk"})
    public ResponseEntity<String> checkItem(@RequestParam("itemId") Long itemId, @RequestParam("isOk") Boolean isOk) {
        //todo its fake
        return null;
    }
}
