package com.shop.inditex.domain.service;

import com.shop.inditex.domain.dto.PriceDto;
import com.shop.inditex.domain.repository.IPriceRepository;
import com.shop.inditex.exception.InditexBusinessException;
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
        PriceDto price = priceRepository.getCorrectPrice(date, productId, brandId);
        if (price == null) {
            throw new InditexBusinessException("Error getting prices with Date: " + date + "productId" + productId + "BrandId" + brandId);
        }
        return price;
    }

}
