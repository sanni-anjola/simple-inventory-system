package com.example.iteminventory.controller;

import com.example.iteminventory.services.InventoryItemService;
import com.example.iteminventory.services.InventoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class InventoryControllerTest {

    @Autowired
    private InventoryController controller;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private InventoryService inventoryService;

    @MockBean
    private InventoryItemService inventoryItemService;

    @Test
    void contextLoads() {
        assertThat(controller).isNotNull();
    }

    @Test
    void testHello() throws Exception {
        this.mockMvc.perform(get("/api/v1/hello"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("hello world")));
    }
    @Test
    void viewAll() {
    }

    @Test
    void addItem() {
    }

    @Test
    void removeItem() {
    }

    @Test
    void deleteItem() throws Exception {
        doAnswer(invocationOnMock -> {
            Object argument = invocationOnMock.getArgument(0);
            assertThat(argument).isEqualTo("electronics");
            return null;
        }).when(inventoryService).deleteItem(anyString());

        this.mockMvc.perform(delete("/api/v1/electronics"))
                .andDo(print())
                .andExpect(status().isNoContent());


    }

    @Test
    void writeToFile() {
    }
}