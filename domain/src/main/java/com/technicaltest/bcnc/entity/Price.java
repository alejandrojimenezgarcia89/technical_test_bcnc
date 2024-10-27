package com.technicaltest.bcnc.entity;

import lombok.Builder;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
@Builder
public class Price {

    private int brandId;
    private OffsetDateTime startDate;
    private OffsetDateTime endDate;
    private int priceList;
    private long productId;
    private int priority;
    private double productPrice;
    private String currency;
}
