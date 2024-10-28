package com.technicaltest.bcnc.adapter;

import com.technicaltest.bcnc.entity.Price;
import com.technicaltest.bcnc.repository.JdbcPriceRepository;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.OffsetDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PriceRepositoryAdapterTest {

    @InjectMocks
    private PriceRepositoryAdapter priceRepositoryAdapter;


    @Mock
    private JdbcPriceRepository jdbcPriceRepository;

    @Test
    void foundApplicablePrice() {
        var highestPriorityPrice = Instancio.create(Price.class);
        when(jdbcPriceRepository.findHighestPriorityPrice(anyLong(), anyInt(), any(OffsetDateTime.class))).thenReturn(highestPriorityPrice);
        var foundPrice = this.priceRepositoryAdapter.findApplicablePrice(1, 1, OffsetDateTime.now());
        assertEquals(foundPrice, highestPriorityPrice);
    }
}