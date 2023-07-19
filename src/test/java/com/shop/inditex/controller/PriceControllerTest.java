package com.shop.inditex.controller;

import com.shop.inditex.domain.dto.PriceDto;
import com.shop.inditex.domain.service.PriceService;
import com.shop.inditex.web.controller.PriceController;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.util.LinkedMultiValueMap;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(controllers = PriceController.class)
@ExtendWith(SpringExtension.class)
@PropertySource("classpath:application.properties")
public class PriceControllerTest {

    @MockBean
    private PriceService priceService;


    @Autowired
    MockMvc mockMvc;

    private PriceDto priceDto;
    private LinkedMultiValueMap<String, String> requestParams;

    @BeforeEach
    public void setup() {
        requestParams = new LinkedMultiValueMap<>();
        requestParams.add("applicationDate", "2020-10-12T07:30:10Z");
        requestParams.add("productId", "35455");
        requestParams.add("brandId", "1");
        priceDto = new PriceDto(
                "1",
                LocalDateTime.of(2020, 6, 14, 0, 0, 0),
                LocalDateTime.of(2020, 6, 14, 0, 0, 0),
                "1", "35455", new BigDecimal(35.50));
    }


    @Test
    @DisplayName("Test - Should return a Single-object object when the parameters sent are correct.")
    public void testValueReturningObject() throws Exception {
        Mockito.when(priceService.getCorrectPrice(
                LocalDateTime.of(2020, 10, 12, 7, 30, 10, 000000),
                35455,
                1)).thenReturn(priceDto);
        mockMvc.perform(get("/api/get-prices")
                        .with(user("admin").password("admin123"))
                        .params(requestParams))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.brandId").value(1))
                .andDo(print());

    }

    @Test
    @DisplayName("Test - Should return badRequest when the endpoint is called without values")
    public void testGetEmptyCurrencies() throws Exception {
        Mockito.when(priceService.getCorrectPrice(
                LocalDateTime.of(2020, 10, 12, 7, 30, 10, 000000),
                35455,
                1)).thenReturn(priceDto);
        mockMvc.perform(get("/api/get-prices")
                        .with(user("admin").password("admin123")))
                .andExpect(status().isBadRequest());

    }

    @Test
    @DisplayName("Test - Should return an exception when the service return a exception")
    public void testGetException() throws Exception {
        Mockito.when(priceService.getCorrectPrice(null, null, null)).thenThrow(NullPointerException.class);
        try {
            mockMvc.perform(get("/api/get-prices").
                            params(requestParams)
                            .with(user("admin").password("admin123")))
                    .andExpect(status().isOk());
        } catch (Exception ex) {
            Assertions.assertThat(ex.getCause() instanceof NullPointerException).isTrue();
        }

    }

    @Test
    @DisplayName("Test - Should return not found when the url is not correct")
    public void testGetExceptionUrl() throws Exception {
        LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("applicationDate", "2021-10-12T07:30:10Z");
        requestParams.add("productId", "0");
        requestParams.add("brandId", "1");
        Mockito.when(priceService.getCorrectPrice(
                LocalDateTime.of(2020, 10, 12, 7, 30, 10, 000000),
                35455,
                1)).thenReturn(priceDto);
        mockMvc.perform(get("/api/getPrices").
                        params(requestParams)
                        .with(user("admin").password("admin123")))
                .andExpect(status().isNotFound());
    }

}