package com.example.iteminventory.services;

import com.example.iteminventory.data.Operation;
import com.example.iteminventory.data.model.InventoryItem;
import com.example.iteminventory.data.repository.InventoryItemRepository;
import com.example.iteminventory.data.repository.InventoryRepository;
import com.example.iteminventory.services.dto.InventoryItemRequest;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
class InventoryItemServiceTest {

    @Autowired
    private InventoryItemService inventoryItemService;
    @Autowired
    private InventoryItemRepository inventoryItemRepository;
    @Autowired
    private InventoryRepository inventoryRepository;

    @AfterEach
    void tearDown() {
        inventoryItemRepository.deleteAll();
        inventoryRepository.deleteAll();
    }

    @Test
    @DisplayName("Test that Item can be saved")
    void save() {
        InventoryItemRequest inventoryItemRequest = new InventoryItemRequest();
        inventoryItemRequest.setPrice(new BigDecimal("100.00"));
        inventoryItemRequest.setDescription("Electronics");
        inventoryItemRequest.setQuantity(7L);
        inventoryItemRequest.setProductName("television set");

        InventoryItem inv1 = inventoryItemService.addItem(inventoryItemRequest);

        assertThat(inventoryItemRepository.findAll()).hasSize(1);
        assertThat(inventoryRepository.findAll()).hasSize(1);

        assertThat(inventoryRepository.findBySlug(inv1.getSlug())).isNotNull();
        assertThat(inventoryRepository.findBySlug(inv1.getSlug()).getCurrentQuantity()).isEqualTo(7);

    }

    @Test
    @DisplayName("Test that Item can be updated")
    void addItem() {
        InventoryItemRequest inventoryItemRequest = new InventoryItemRequest();
        inventoryItemRequest.setPrice(new BigDecimal("100.00"));
        inventoryItemRequest.setDescription("Electronics");
        inventoryItemRequest.setQuantity(7L);
        inventoryItemRequest.setProductName("television set");

        InventoryItem inv1 = inventoryItemService.addItem(inventoryItemRequest);
        InventoryItem inv2 = inventoryItemService.addItem(inventoryItemRequest);

        assertThat(inventoryItemRepository.findAll()).hasSize(2);
        assertThat(inventoryRepository.findAll()).hasSize(1);

        assertThat(inventoryRepository.findBySlug(inv1.getSlug())).isNotNull();
        assertThat(inventoryRepository.findBySlug(inv2.getSlug())).isNotNull();
        assertThat(inventoryRepository.findBySlug(inv2.getSlug()).getCurrentQuantity()).isEqualTo(14);
        assertThat(inv1.getOperation()).isEqualTo(Operation.ADD);
    }

    @Test
    @DisplayName("Test that Item can be removed")
    void removeItem() {
        InventoryItemRequest inventoryItemRequest = new InventoryItemRequest();
        inventoryItemRequest.setPrice(new BigDecimal("100.00"));
        inventoryItemRequest.setDescription("Electronics");
        inventoryItemRequest.setQuantity(7L);
        inventoryItemRequest.setProductName("television set");

        InventoryItem inv1 = inventoryItemService.addItem(inventoryItemRequest);
        inventoryItemRequest.setQuantity(4L);
        InventoryItem inv2 = inventoryItemService.removeItem(inventoryItemRequest);

        assertThat(inventoryItemRepository.findAll()).hasSize(2);
        assertThat(inventoryRepository.findAll()).hasSize(1);

        assertThat(inventoryRepository.findBySlug(inv1.getSlug())).isNotNull();
        assertThat(inventoryRepository.findBySlug(inv2.getSlug())).isNotNull();
        assertThat(inventoryRepository.findBySlug(inv2.getSlug()).getCurrentQuantity()).isEqualTo(3);
        assertThat(inv1.getOperation()).isEqualTo(Operation.ADD);
        assertThat(inv2.getOperation()).isEqualTo(Operation.REMOVE);
    }



}
