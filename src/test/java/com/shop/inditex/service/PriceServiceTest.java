package com.shop.inditex.service;

import com.shop.inditex.domain.dto.PriceDto;
import com.shop.inditex.domain.repository.IPriceRepository;
import com.shop.inditex.domain.service.PriceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class PriceServiceTest {

    @InjectMocks
    private PriceService currencyService;

    @Mock
    private IPriceRepository priceRepository;

    private PriceDto priceDto;


    @BeforeEach
    public void setup() {
        priceDto = new PriceDto(
                "1",
                LocalDateTime.of(2020, 6, 14, 0, 0, 0),
                LocalDateTime.of(2020, 10, 30, 0, 0, 0),
                "1", "35455", new BigDecimal(35.50));
    }

    @Test
    @DisplayName("Test Should work correctly when the parameters are correct")
    public void testGetCorrectPrice() {
        LocalDateTime date = LocalDateTime.of(2020, 6, 14, 0, 0, 0);
        when(priceRepository.getCorrectPrice(date, 35455, 1)).thenReturn(priceDto);
        PriceDto response = currencyService.getCorrectPrice(date, 35455, 1);
        assertEquals(priceDto, response);
    }


    @Test
    @DisplayName("Test testGetCorrectPrice when the repository return some exception")
    public void testGetAllCurrenciesException() {
        LocalDateTime date = LocalDateTime.now();
        when(priceRepository.getCorrectPrice(date, 35455, 1)).thenThrow(NullPointerException.class);
        try {
            currencyService.getCorrectPrice(date, 35455, 1);
        } catch (Exception ex) {
            assertTrue(ex instanceof NullPointerException);
        }
    }

}
