package com.technicaltest.bcnc.usecase;

import com.technicaltest.bcnc.entity.Price;
import com.technicaltest.bcnc.model.PriceRequest;
import com.technicaltest.bcnc.port.input.SearchPriceUseCase;
import com.technicaltest.bcnc.repository.PriceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SearchPriceUseCaseImpl implements SearchPriceUseCase {

    private final PriceRepository priceRepository;

    @Override
    public Price handle(PriceRequest priceRequest) {
        return priceRepository.findApplicablePrice(priceRequest.productId(), priceRequest.brandId(), priceRequest.applicationDate());
    }
}
