package com.example.iteminventory.services;

import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public interface InventoryService {
    void writeToCsv() throws IOException;
}
