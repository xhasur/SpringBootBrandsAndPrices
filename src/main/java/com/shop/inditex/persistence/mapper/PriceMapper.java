package com.shop.inditex.persistence.mapper;

import com.shop.inditex.domain.dto.PriceDto;
import com.shop.inditex.persistence.entity.PriceEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface PriceMapper {

    PriceDto toPriceDto(PriceEntity price);

    List<PriceDto> toPrices(List<PriceEntity> price);

    @InheritInverseConfiguration
    @Mapping(target = "brandId", ignore = true)
    PriceEntity toPriceEntity(PriceDto price);
}