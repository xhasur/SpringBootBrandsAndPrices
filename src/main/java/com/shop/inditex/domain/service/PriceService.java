package com.shop.inditex.domain.service;

import com.shop.inditex.domain.dto.PriceDto;
import com.shop.inditex.domain.repository.IPriceRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PriceService {

    private static Logger LOGGER = LogManager.getLogger(PriceService.class);

    private IPriceRepository priceRepository;

    public PriceService(IPriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }


    public PriceDto getCorrectPrice(LocalDateTime date, Integer productId, Integer brandId) {
        LOGGER.info("PriceService::getCorrectPrice {} ", date, productId, brandId);
        return priceRepository.getCorrectPrice(date, productId, brandId);
    }

}
