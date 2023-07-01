package com.shop.inditex.persistence;

import com.shop.inditex.domain.dto.PriceDto;
import com.shop.inditex.domain.repository.IPriceRepository;
import com.shop.inditex.persistence.crud.PriceCrudRepository;
import com.shop.inditex.persistence.entity.PriceEntity;
import com.shop.inditex.persistence.mapper.PriceMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public class PriceRepository implements IPriceRepository {

    private static Logger LOGGER = LogManager.getLogger(PriceRepository.class);

    private PriceCrudRepository priceCrudRepository;

    private PriceMapper priceMapper;

    public PriceRepository(PriceCrudRepository priceCrudRepository, PriceMapper priceMapper) {
        this.priceCrudRepository = priceCrudRepository;
        this.priceMapper = priceMapper;
    }

    @Override
    public PriceDto getCorrectPrice(LocalDateTime date, Integer productId, Integer brandId) {
        LOGGER.info("PriceRepository::getCorrectPrice {} ", date, productId, brandId);
        Optional<List<PriceEntity>> prices = priceCrudRepository.getPrices(date, productId, brandId);
        return prices
                .filter(list -> !list.isEmpty())
                .map(list -> list.get(0))
                .map(this.priceMapper::toPriceDto)
                .orElse(null);
    }


}
