package com.technicaltest.bcnc.repository;

import com.technicaltest.bcnc.entity.Price;

import java.time.OffsetDateTime;

public interface JdbcPriceRepository {

    Price findHighestPriorityPrice(long productId, int brandId, OffsetDateTime applicationDate);
}
