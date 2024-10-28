package com.technicaltest.bcnc.apirest.controller;

import com.technicaltest.bcnc.apirest.api.PriceApi;
import com.technicaltest.bcnc.apirest.mapper.PriceDtoMapper;
import com.technicaltest.bcnc.apirest.model.PriceResponseDTO;
import com.technicaltest.bcnc.entity.Price;
import com.technicaltest.bcnc.model.PriceRequest;
import com.technicaltest.bcnc.port.input.SearchPriceUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.OffsetDateTime;

@RestController
@RequiredArgsConstructor
public class PriceController implements PriceApi {

    private final SearchPriceUseCase searchPriceUseCase;

    private final PriceDtoMapper priceDtoMapper;

    @Override
    public ResponseEntity<PriceResponseDTO> getHighestPriorityPrice(Long productId, Integer brandId, OffsetDateTime applicationDate) {

        Price price = searchPriceUseCase.handle(new PriceRequest(productId,brandId,applicationDate));

        return ResponseEntity.ok(priceDtoMapper.asPriceResponseDTO(price));
    }
}
