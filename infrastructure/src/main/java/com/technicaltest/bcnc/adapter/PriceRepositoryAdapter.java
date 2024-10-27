package com.technicaltest.bcnc.adapter;

import com.technicaltest.bcnc.entity.Price;
import com.technicaltest.bcnc.exception.PriceNotFoundException;
import com.technicaltest.bcnc.repository.PriceRepository;
import com.technicaltest.bcnc.repository.JdbcPriceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;

@Component
@RequiredArgsConstructor
public class PriceRepositoryAdapter implements PriceRepository {

    private final JdbcPriceRepository jdbcPriceRepository;

    @Override
    public Price findApplicablePrice(long productId, int brandId, OffsetDateTime applicationDate) {
        return jdbcPriceRepository.findHighestPriorityPrice(productId, brandId, applicationDate);
    }
}
