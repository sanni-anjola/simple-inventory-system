package com.example.iteminventory.data.repository;

import com.example.iteminventory.data.model.InventoryItem;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


//@Sql(scripts = {"/db/insert.sql"})
@SpringBootTest
class InventoryItemRepositoryTest {
    @Autowired
    private InventoryItemRepository inventoryItemRepository;
    @Test
    void test(){
        System.out.println(inventoryItemRepository.findAll());
    }

    @Test
    void findBySlug() {
        List<InventoryItem> coca_colas = inventoryItemRepository.findBySlug("coca_cola");
        assertThat(coca_colas).isNotNull();
        assertThat(coca_colas).hasSize(4);
    }

    @Test
    void getMax() {
        int coca_cola = inventoryItemRepository.getMax("coca_cola");
        assertThat(coca_cola).isEqualTo(10);
    }

    @Test
    void getMin() {
        int coca_cola = inventoryItemRepository.getMin("coca_cola");
        assertThat(coca_cola).isEqualTo(1);
    }

    @Test
    void getAvg() {
        int coca_cola = inventoryItemRepository.getAvg("coca_cola");
        assertThat(coca_cola).isEqualTo(5);
    }

    @Test
    void getSum() {
        int coca_cola = inventoryItemRepository.getSum("coca_cola");
        assertThat(coca_cola).isEqualTo(20);
    }

    @Test
    void testDb() {
        InventoryItem inventoryItem = new InventoryItem();
        inventoryItem.setDescription("Coke");
        inventoryItem.setPrice(BigDecimal.valueOf(100));
        inventoryItem.setProductName("Coca cola");
        inventoryItemRepository.save(inventoryItem);
        assertThat(inventoryItemRepository.findBySlug("coca_cola")).contains(inventoryItem);
    }
}