package com.example.iteminventory.data.repository;

import com.example.iteminventory.data.model.InventoryItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;

public interface InventoryItemRepository extends JpaRepository<InventoryItem, Long> {
    List<InventoryItem> findBySlug(String slug);

    @Query("select max(inv.quantity) from InventoryItem inv where inv.slug = ?1")
    int getMax(String slug);
    @Query("select min(inv.quantity) from InventoryItem inv where inv.slug = ?1")
    int getMin(String slug);
    @Query("select avg(inv.quantity) from InventoryItem inv where inv.slug = ?1")
    int getAvg(String slug);
    @Query("select sum(inv.quantity) from InventoryItem inv where inv.slug = ?1")
    int getSum(String slug);

}
