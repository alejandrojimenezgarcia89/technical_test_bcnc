package com.technicaltest.bcnc.apirest.mapper;

import com.technicaltest.bcnc.apirest.model.PriceResponseDTO;
import com.technicaltest.bcnc.entity.Price;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface PriceDtoMapper {

    @Mapping(target = "price", source = "productPrice")
    PriceResponseDTO asPriceResponseDTO(Price price);
}
