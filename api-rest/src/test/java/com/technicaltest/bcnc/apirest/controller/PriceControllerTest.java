package com.technicaltest.bcnc.apirest.controller;

import com.technicaltest.bcnc.apirest.mapper.PriceDtoMapper;
import com.technicaltest.bcnc.apirest.model.PriceResponseDTO;
import com.technicaltest.bcnc.entity.Price;
import com.technicaltest.bcnc.model.PriceRequest;
import com.technicaltest.bcnc.port.input.SearchPriceUseCase;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.time.OffsetDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PriceControllerTest {

    @Mock
    private SearchPriceUseCase searchPriceUseCase;

    @Spy
    private PriceDtoMapper priceDtoMapper = Mappers.getMapper(PriceDtoMapper.class);

    @InjectMocks
    private PriceController priceController;

    @Test
    void testGetHighestPriorityPrice() {
        var price = Instancio.create(Price.class);

        when(searchPriceUseCase.handle(any(PriceRequest.class))).thenReturn(price);

        ResponseEntity<PriceResponseDTO> response = priceController.getHighestPriorityPrice(price.getProductId(), price.getBrandId(), OffsetDateTime.now());

        assertEquals(ResponseEntity.ok(this.priceDtoMapper.asPriceResponseDTO(price)), response);
    }
}
