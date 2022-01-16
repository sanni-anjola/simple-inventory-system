package com.example.iteminventory.services;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
@Slf4j
@SpringBootTest
class InventoryServiceImplTest {
    @Autowired
    private InventoryService inventoryService;

    @Test
    void name() throws IOException {
        inventoryService.writeToCsv();

    }
}