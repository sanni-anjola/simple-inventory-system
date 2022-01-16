package com.example.iteminventory.services;

import com.example.iteminventory.data.model.InventoryItem;
import com.example.iteminventory.data.repository.InventoryItemRepository;
import com.example.iteminventory.data.repository.InventoryRepository;
import com.example.iteminventory.services.dto.InventoryItemRequest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@Slf4j
@SpringBootTest
class InventoryServiceImplTest {
    @Autowired
    private InventoryService inventoryService;
    @Autowired
    private InventoryItemService inventoryItemService;
    @Autowired
    private InventoryRepository inventoryRepository;
    @Autowired
    private InventoryItemRepository inventoryItemRepository;

    @BeforeEach
    void setUp(){
        InventoryItemRequest inventoryItemRequest = new InventoryItemRequest();
        inventoryItemRequest.setPrice(new BigDecimal("100.00"));
        inventoryItemRequest.setDescription("Electronics");
        inventoryItemRequest.setQuantity(7L);
        inventoryItemRequest.setProductName("television set");

        InventoryItem inv1 = inventoryItemService.addItem(inventoryItemRequest);
        InventoryItem inv2 = inventoryItemService.addItem(inventoryItemRequest);

        InventoryItemRequest inventoryItemRequest2 = new InventoryItemRequest();
        inventoryItemRequest2.setPrice(new BigDecimal("1200.00"));
        inventoryItemRequest2.setDescription("Gadgets");
        inventoryItemRequest2.setQuantity(10L);
        inventoryItemRequest2.setProductName("Ipod");

        InventoryItem inv3 = inventoryItemService.addItem(inventoryItemRequest2);
    }

    @AfterEach
    void tearDown() {
        inventoryItemRepository.deleteAll();
        inventoryRepository.deleteAll();
    }

    @Test
    @DisplayName("Test that Item can be deleted from the Inventory")
    void testDeleteItem() {
//        Given
        assertThat(inventoryRepository.findAll()).hasSize(2);
        assertThat(inventoryItemRepository.findAll()).hasSize(3);
//        When
        inventoryService.deleteItem("television set");
//        Assert
        assertThat(inventoryService.getAllInventories()).hasSize(1);
        assertThat(inventoryItemService.getAll()).hasSize(4);
    }

    @Test
    void testWriteToFile() throws IOException {
        inventoryService.writeToCsv();
    }
}