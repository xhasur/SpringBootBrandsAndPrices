package com.shop.inditex.persistence.crud;

import com.shop.inditex.persistence.entity.PriceEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface PriceCrudRepository extends CrudRepository<PriceEntity, Integer> {

    @Query(" select p from PriceEntity p " +
            "where p.startDate <= :time and :time <= p.endDate " +
            "and productId = :productId " +
            "and brandId = :brandId " +
            "ORDER BY p.priority desc")
    Optional<List<PriceEntity>> getPrices(@Param("time") LocalDateTime time,
                                          @Param("productId") Integer productId,
                                          @Param("brandId") Integer brandId);
}
