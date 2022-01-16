package com.example.iteminventory.services;

import com.example.iteminventory.data.Operation;
import com.example.iteminventory.data.model.Inventory;
import com.example.iteminventory.data.repository.InventoryRepository;
import com.example.iteminventory.exception.ApplicationException;
import com.example.iteminventory.services.dto.InventoryItemRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class InventoryServiceImpl implements InventoryService {
    private final InventoryRepository inventoryRepository;
    private final InventoryItemService inventoryItemService;


    @Override
    public List<Inventory> getAllInventories() {
        return inventoryRepository.findAll();
    }

    @Override
    public void deleteItem(String itemName) {
        String slug = itemName.toLowerCase().replace(" ", "_");
        Inventory inventoryToDelete = inventoryRepository.findBySlug(slug);
        if(inventoryToDelete == null) throw new ApplicationException("No such Item");
        InventoryItemRequest inventoryItemRequest = new InventoryItemRequest();
        inventoryItemRequest.setQuantity(inventoryToDelete.getCurrentQuantity());
        inventoryItemRequest.setProductName(itemName);
        inventoryItemService.removeItem(inventoryItemRequest);
        inventoryRepository.deleteBySlug(slug);
    }

    @Override
    public void writeToCsv() throws IOException {

        File csvOutputFile = new File("inventory.csv");
        if(!csvOutputFile.exists()) {
            boolean newFile = csvOutputFile.createNewFile();
        }

        try (PrintWriter pw = new PrintWriter(csvOutputFile)) {
            inventoryRepository.findAll().stream()
                    .map(Inventory::toCsvRow)
                    .forEach(pw::println);
        }
//        return recordAsCsv;
    }


}