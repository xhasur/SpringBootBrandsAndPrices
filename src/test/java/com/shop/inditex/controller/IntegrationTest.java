package com.shop.inditex.controller;

import com.shop.inditex.domain.service.PriceService;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.util.LinkedMultiValueMap;

import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@SqlGroup({
        @Sql(value = "classpath:schema.sql", executionPhase = BEFORE_TEST_METHOD),
        @Sql(value = "classpath:data.sql", executionPhase = BEFORE_TEST_METHOD)
})
public class IntegrationTest {

    @Resource
    private PriceService priceService;

    @Autowired
    MockMvc mockMvc;

    private LinkedMultiValueMap<String, String> requestParams;

    @BeforeEach
    public void setup() {
        requestParams = new LinkedMultiValueMap<>();
        requestParams.add("applicationDate", "2020-10-12T07:30:10Z");
        requestParams.add("productId", "35455");
        requestParams.add("brandId", "1");
    }


    @Test
    void should_retrieve_one_user() throws Exception {
        mockMvc.perform(get("/api/get-prices")
                        .params(requestParams))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.brandId").value(1));
    }


}