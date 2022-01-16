package com.example.iteminventory.controller;

import com.example.iteminventory.data.model.Inventory;
import com.example.iteminventory.services.InventoryItemService;
import com.example.iteminventory.services.InventoryService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//@WebMvcTest
@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
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
//    @Test
//    void viewAll() throws Exception {
//        MvcResult result = this.mockMvc.perform(get("/api/v1/"))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andReturn();
//        ObjectMapper mapper = new ObjectMapper();
//        List<Inventory> actual = mapper.readValue(result.getResponse().getContentAsString(), new TypeReference<List<Inventory>>() {});
//        log.info("view all -> {}", actual);
//    }

    @Test
    void viewAll() throws Exception {
        Inventory inv1 = new Inventory();
        inv1.setCurrentQuantity(20L);
        inv1.setSlug("perfume");
        inv1.setId(1L);

        Inventory inv2 = new Inventory();
        inv2.setCurrentQuantity(10L);
        inv2.setSlug("make_ups");
        inv2.setId(2L);

        doReturn(List.of(inv1, inv2)).when(inventoryService).getAllInventories();

        this.mockMvc.perform(get("/api/v1/"))
                .andDo(print())
                // test status
                .andExpect(status().isOk())
                // test contentType
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                // test returned response
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].currentQuantity", is(20)))
                .andExpect(jsonPath("$[0].slug", is("perfume")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].currentQuantity", is(10)))
                .andExpect(jsonPath("$[1].slug", is("make_ups")));

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