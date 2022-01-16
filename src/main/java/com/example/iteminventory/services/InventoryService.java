package com.example.iteminventory.services;

import com.example.iteminventory.data.model.Inventory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public interface InventoryService {
    List<Inventory> getAllInventories();
    void deleteItem(String itemName);
    void writeToCsv() throws IOException;

}
