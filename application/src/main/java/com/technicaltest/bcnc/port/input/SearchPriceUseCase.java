package com.technicaltest.bcnc.port.input;

import com.technicaltest.bcnc.entity.Price;
import com.technicaltest.bcnc.model.PriceRequest;

public interface SearchPriceUseCase {
    Price handle(PriceRequest priceRequest);
}
