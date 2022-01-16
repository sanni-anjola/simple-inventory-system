package com.example.iteminventory.services.dto;

import com.example.iteminventory.data.Operation;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class InventoryItemRequest {
    private String productName;
    private Long quantity;
    private BigDecimal price;
    private String description;
    private Operation operation;
}
