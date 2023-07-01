package com.shop.inditex.domain.repository;

import com.shop.inditex.domain.dto.PriceDto;

import java.time.LocalDateTime;

public interface IPriceRepository {
    PriceDto getCorrectPrice(LocalDateTime date, Integer productId, Integer brandId);

}


