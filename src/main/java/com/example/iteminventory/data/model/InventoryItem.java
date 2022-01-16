package com.example.iteminventory.data.model;

import com.example.iteminventory.data.Operation;
import com.example.iteminventory.entityListener.InventoryItemListener;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@EntityListeners(value = InventoryItemListener.class)
@Entity
public class InventoryItem {
    @Id
    @GeneratedValue
    private Long id;
    private String productName;
    private BigDecimal price;
    private String slug;
    private Long quantity;
    private String description;
    private LocalDateTime time;
    @ManyToOne(fetch = FetchType.LAZY)
    private Inventory inventory;
    @Enumerated(EnumType.ORDINAL)
    private Operation operation;

    @PrePersist
    public void saveSlug(){
        this.slug = this.productName.toLowerCase().replace(" ", "_");
    }

}
