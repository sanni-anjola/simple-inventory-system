package com.example.iteminventory.services;

import com.example.iteminventory.data.model.Inventory;
import com.example.iteminventory.data.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

@RequiredArgsConstructor
@Service
public class InventoryServiceImpl implements InventoryService {
    private final InventoryRepository inventoryRepository;
    @Override
    public void writeToCsv() throws IOException {
//        String recordAsCsv = inventoryRepository.findAll().stream()
//                .map(Inventory::toCsvRow)
//                .collect(Collectors.joining(System.getProperty("line.separator")));

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