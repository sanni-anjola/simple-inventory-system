package com.example.iteminventory.controller;

import com.example.iteminventory.data.model.InventoryItem;
import com.example.iteminventory.exception.ApplicationException;
import com.example.iteminventory.services.InventoryItemService;
import com.example.iteminventory.services.InventoryService;
import com.example.iteminventory.services.dto.InventoryItemRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1")
public class InventoryController {
    private final InventoryService inventoryService;
    private final InventoryItemService inventoryItemService;

    @GetMapping("/hello")
    public ResponseEntity<String> hello(){
        return ResponseEntity.ok().body("hello world");
    }

    @GetMapping
    public ResponseEntity<?> viewAll(){
        return ResponseEntity.ok().body(inventoryService.getAllInventories());
    }

    @PostMapping("/addItem")
    public ResponseEntity<?> addItem(@RequestBody InventoryItemRequest inventoryItemRequest){
        try {
            InventoryItem inventoryItem = inventoryItemService.addItem(inventoryItemRequest);
            return ResponseEntity
                   .ok()
                   .body(inventoryItem);
        }catch (ApplicationException ex){
            return ResponseEntity.badRequest().body(ex.getLocalizedMessage());
        }
    }

    @PostMapping("/removeItem")
    public ResponseEntity<?> removeItem(@RequestBody InventoryItemRequest inventoryItemRequest){
        try {
            InventoryItem inventoryItem = inventoryItemService.removeItem(inventoryItemRequest);
            return ResponseEntity
                    .ok()
                    .body(inventoryItem);
        }catch (ApplicationException ex){
            return ResponseEntity.badRequest().body(ex.getLocalizedMessage());
        }
    }
    @DeleteMapping("/{productName}")
    public ResponseEntity<?> deleteItem(@PathVariable String productName){
        inventoryService.deleteItem(productName);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/downloadCsv")
    public ResponseEntity<?> writeToFile(){
        try {
            inventoryService.writeToCsv();
            return ResponseEntity.ok().body("File download successful, check inventory.csv in the program directory");
        }catch (IOException ex){
            return ResponseEntity.badRequest().body("Error processing request");
        }
    }
}
