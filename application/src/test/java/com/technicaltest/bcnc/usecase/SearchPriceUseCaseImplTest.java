package com.technicaltest.bcnc.usecase;

import com.technicaltest.bcnc.model.PriceRequest;
import com.technicaltest.bcnc.repository.PriceRepository;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.OffsetDateTime;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class SearchPriceUseCaseImplTest {

    @Mock
    private PriceRepository priceRepository;

    @InjectMocks
    private SearchPriceUseCaseImpl searchPriceUseCase;

    @Test
    void handle() {
        var priceRequest = Instancio.create(PriceRequest.class);
        searchPriceUseCase.handle(priceRequest);
        verify(this.priceRepository).findApplicablePrice(priceRequest.productId(), priceRequest.brandId(), priceRequest.applicationDate());
    }
}