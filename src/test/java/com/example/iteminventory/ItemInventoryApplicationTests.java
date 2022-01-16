package com.example.iteminventory;

import com.example.iteminventory.data.model.InventoryItem;
import com.example.iteminventory.services.InventoryItemService;
import com.example.iteminventory.services.dto.InventoryItemRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
class ItemInventoryApplicationTests {

    @Autowired
    private InventoryItemService inventoryItemService;
    @Test
    void save() {
        InventoryItemRequest inventoryItemRequest = new InventoryItemRequest();
        inventoryItemRequest.setPrice(new BigDecimal("100.00"));
        inventoryItemRequest.setDescription("Electronics");
        inventoryItemRequest.setQuantity(7L);
        inventoryItemRequest.setProductName("television set");

        InventoryItem inv1 = inventoryItemService.addItem(inventoryItemRequest);
//        assertThat()

    }

}
