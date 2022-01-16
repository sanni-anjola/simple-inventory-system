package com.example.iteminventory.services;

import com.example.iteminventory.data.model.InventoryItem;
import com.example.iteminventory.services.dto.InventoryItemRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface InventoryItemService {
//    InventoryItem save(InventoryItemRequest inventoryItemRequest);
    InventoryItem addItem(InventoryItemRequest inventoryItemRequest);
    InventoryItem removeItem(InventoryItemRequest inventoryItemRequest);
    List<InventoryItem> getAll();
}
