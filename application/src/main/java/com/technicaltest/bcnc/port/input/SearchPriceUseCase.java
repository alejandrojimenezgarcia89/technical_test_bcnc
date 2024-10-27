package com.technicaltest.bcnc.port.input;

import com.technicaltest.bcnc.entity.Price;
import com.technicaltest.bcnc.model.PriceRequest;

import java.time.OffsetDateTime;

public interface SearchPriceUseCase {
    Price handle(PriceRequest priceRequest);
}
