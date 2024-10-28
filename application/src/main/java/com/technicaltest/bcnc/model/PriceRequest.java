package com.technicaltest.bcnc.model;

import java.time.OffsetDateTime;

public record PriceRequest(long productId, int brandId, OffsetDateTime applicationDate) {
}
