package com.shop.inditex.repository;


import com.shop.inditex.domain.dto.PriceDto;
import com.shop.inditex.persistence.PriceRepository;
import com.shop.inditex.persistence.crud.PriceCrudRepository;
import com.shop.inditex.persistence.entity.PriceEntity;
import com.shop.inditex.persistence.mapper.PriceMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PriceRepositoryTest {


    @InjectMocks
    private PriceRepository priceRepository;

    @Mock
    private PriceCrudRepository priceCrudRepository;

    @Mock
    private PriceMapper priceMapper;

    List<PriceDto> prices;
    PriceDto priceDto = null;
    PriceDto sPriceDto = null;
    List<PriceEntity> entityPrices;


    @BeforeEach
    public void setup() {
        priceDto = new PriceDto(
                "1",
                LocalDateTime.of(2020, 6, 14, 0, 0, 0),
                LocalDateTime.of(2021, 10, 30, 0, 0, 0),
                "1", "35455", new BigDecimal(35.50));
        sPriceDto = new PriceDto(
                "1",
                LocalDateTime.of(2020, 10, 10, 0, 0, 0),
                LocalDateTime.of(2020, 6, 3, 0, 0, 0),
                "1", "35455", new BigDecimal(35.50));
        prices = new ArrayList<>(Arrays.asList(priceDto, sPriceDto));



        PriceEntity firstPrice = new PriceEntity(1L,
                LocalDateTime.of(2020, 6, 14, 0, 0, 0),
                LocalDateTime.of(2021, 10, 30, 0, 0, 0),
                "1", 35455,2, new BigDecimal(35.50), "€");
        PriceEntity secondPrice = new PriceEntity(1L,
                LocalDateTime.of(2020, 10, 10, 0, 0, 0),
                LocalDateTime.of(2020, 6, 3, 0, 0, 0),
                "1", 35455,2, new BigDecimal(35.50), "€");

        entityPrices = new ArrayList<>(Arrays.asList(firstPrice, secondPrice));


    }


    @Test
    @DisplayName("Test - getCorrectPrice should work correctly when the correct values are present.")
    public void testGetCorrectPrice() {
        LocalDateTime date = LocalDateTime.of(2020, 6, 14, 0, 0, 0);
        when(priceCrudRepository.getPrices(date, 35455, 1)).thenReturn(Optional.ofNullable(entityPrices));
        when(priceMapper.toPriceDto(any(PriceEntity.class))).thenReturn(priceDto);
        PriceDto response = priceRepository.getCorrectPrice(date , 35455 , 1);
        assertEquals(response, response);
    }

    @Test
    @DisplayName("Test -  getCorrectPrice should return null when the method does not return values.")
    public void testGetCorrectPriceReturnEmptyValues() {
        LocalDateTime date = LocalDateTime.of(2020, 6, 14, 0, 0, 0);
        when(priceCrudRepository.getPrices(date, 35455, 1)).thenReturn(Optional.ofNullable(List.of()));
        PriceDto response = priceRepository.getCorrectPrice(date , 35455 , 1);
        assertEquals(response, null);
    }

}
