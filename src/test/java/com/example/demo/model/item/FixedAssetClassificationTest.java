package com.example.demo.model.item;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static com.example.demo.model.item.FixedAssetClassification.LAND;

class FixedAssetClassificationTest {

    @Test
    void getAll() {
        Map<String, String> all = FixedAssetClassification.getAll();

        Assertions.assertEquals(all.get(LAND.toString()), "grunty");
    }
}