package shop.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import shop.dto.GoodsDto;
import shop.service.ShopService;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ShopController.class)
class ShopControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ShopService service;

    private final GoodsDto dto = new GoodsDto(1L, "pr1", new BigDecimal(1));

    @Test
    void getAllGoods() throws Exception {

        var list = List.of(dto);

        when(service.getAllGoods()).thenReturn(list);

        String contentAsString = mockMvc.perform(MockMvcRequestBuilders.get("/get-all-goods"))
                .andExpect(status().is2xxSuccessful())
                .andReturn()
                .getResponse()
                .getContentAsString();

        assertEquals("[{\"productName\":\"pr1\",\"price\":1}]", contentAsString);
    }

    @Test
    void addProduct() throws Exception {
        String contentAsString = mockMvc.perform(MockMvcRequestBuilders
                .post("/add-product")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"productName\": \"iphone 14\", \"price\": 75000}")
        )
                .andExpect(status().is2xxSuccessful())
                .andReturn()
                .getResponse()
                .getContentAsString();

        assertEquals("{\"status\":\"OK\",\"errorDescription\":null}", contentAsString);
    }
}