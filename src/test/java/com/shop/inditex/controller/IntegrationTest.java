package com.shop.inditex.controller;

import com.shop.inditex.domain.service.PriceService;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
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

    @BeforeEach
    public void setup() {
    }

    @Test
    @DisplayName("Test - Should return a correct value ( Test 1: 10:00 - día 14, productId 35455,brand 1 (ZARA))")
    void testCase1() throws Exception {
        LinkedMultiValueMap<String, String> requestParams;
        requestParams = new LinkedMultiValueMap<>();
        requestParams.add("applicationDate", "2020-06-14T10:00:00Z");
        requestParams.add("productId", "35455");
        requestParams.add("brandId", "1");
        mockMvc.perform(get("/api/get-prices")
                        .params(requestParams))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.brandId").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.startDate").value("2020-06-14T00:00:00"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.endDate").value("2020-12-31T23:59:59"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.priceList").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.productId").value(35455))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(35.50));
    }
    @Test
    @DisplayName("Test - Should return a correct value (Test 2: petición a las 16:00 del día 14 del producto 35455   para la brand 1 (ZARA))")
    void testCase2() throws Exception {
        LinkedMultiValueMap<String, String> requestParams;
        requestParams = new LinkedMultiValueMap<>();
        requestParams.add("applicationDate", "2020-06-14T16:00:00Z");
        requestParams.add("productId", "35455");
        requestParams.add("brandId", "1");
        mockMvc.perform(get("/api/get-prices")
                        .params(requestParams))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.brandId").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.startDate").value("2020-06-14T15:00:00"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.endDate").value("2020-06-14T18:30:00"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.priceList").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$.productId").value(35455))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(25.45));
    }

    @Test
    @DisplayName("Test - Should return a correct value (Test 3: petición a las 21:00 del día 14 del producto 35455   para la brand 1 (ZARA))")
    void testCase3() throws Exception {
        LinkedMultiValueMap<String, String> requestParams;
        requestParams = new LinkedMultiValueMap<>();
        requestParams.add("applicationDate", "2020-06-14T21:00:00Z");
        requestParams.add("productId", "35455");
        requestParams.add("brandId", "1");
        mockMvc.perform(get("/api/get-prices")
                        .params(requestParams))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.brandId").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.startDate").value("2020-06-14T00:00:00"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.endDate").value("2020-12-31T23:59:59"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.priceList").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.productId").value(35455))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(35.50));
    }

    @Test
    @DisplayName("Test - Should return a correct value (Test 4: petición a las 10:00 del día 15 del producto 35455   para la brand 1 (ZARA))")
    void testCase4() throws Exception {
        LinkedMultiValueMap<String, String> requestParams;
        requestParams = new LinkedMultiValueMap<>();
        requestParams.add("applicationDate", "2020-06-15T10:00:00Z");
        requestParams.add("productId", "35455");
        requestParams.add("brandId", "1");
        mockMvc.perform(get("/api/get-prices")
                        .params(requestParams))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.brandId").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.startDate").value("2020-06-15T00:00:00"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.endDate").value("2020-06-15T11:00:00"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.priceList").value(3))
                .andExpect(MockMvcResultMatchers.jsonPath("$.productId").value(35455))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(30.50));
    }

    @Test
    @DisplayName("Test - Should return a correct value (Test 5: petición a las 21:00 del día 16 del producto 35455   para la brand 1 (ZARA))")
    void testCase5() throws Exception {
        LinkedMultiValueMap<String, String> requestParams;
        requestParams = new LinkedMultiValueMap<>();
        requestParams.add("applicationDate", "2020-06-16T21:00:00Z");
        requestParams.add("productId", "35455");
        requestParams.add("brandId", "1");
        mockMvc.perform(get("/api/get-prices")
                        .params(requestParams))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.brandId").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.startDate").value("2020-06-15T16:00:00"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.endDate").value("2020-12-31T23:59:59"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.priceList").value(4))
                .andExpect(MockMvcResultMatchers.jsonPath("$.productId").value(35455))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(38.95));
    }
}