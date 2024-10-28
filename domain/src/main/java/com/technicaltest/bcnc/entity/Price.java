package com.technicaltest.bcnc.entity;

import lombok.Builder;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
@Builder
public class Price {

    private long productId;
    private int brandId;
    private OffsetDateTime startDate;
    private OffsetDateTime endDate;
    private int priceList;
    private int priority;
    private double productPrice;
    private String currency;
}
