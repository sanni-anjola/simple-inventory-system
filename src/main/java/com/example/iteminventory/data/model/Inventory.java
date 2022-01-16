package com.example.iteminventory.data.model;

import lombok.Data;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
@Entity
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String slug;

    @OneToMany
    private List<InventoryItem> inventoryItem = new ArrayList<>();

    private LocalDateTime timeOfEntry;
    private Long currentQuantity;

    public String toCsvRow() {
        String name = slug.toUpperCase().replaceAll("_", " ");
        return Stream.of(id, name, currentQuantity, timeOfEntry)
                .map(value -> String.valueOf(value).replaceAll("\"", "\"\""))
                .map(value -> Stream.of("\"", ",").anyMatch(value::contains) ? "\"" + value + "\"" : value)
                .collect(Collectors.joining(","));

    }
}
