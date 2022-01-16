package com.example.iteminventory.data.repository;

import com.example.iteminventory.data.model.Inventory;
import com.example.iteminventory.data.model.InventoryItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    Inventory findBySlug(String slug);
    void deleteBySlug(String slug);
}
