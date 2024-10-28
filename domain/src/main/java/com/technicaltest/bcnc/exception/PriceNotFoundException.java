package com.technicaltest.bcnc.exception;

import java.text.MessageFormat;
import java.time.OffsetDateTime;

public class PriceNotFoundException extends RuntimeException {

    public PriceNotFoundException(String message) {
        super(message);
    }

    public PriceNotFoundException(long productId, int brandId, OffsetDateTime date) {
        this(MessageFormat.format("Price not found for product {0}, brand {1} at date {2}", productId,brandId,date));
    }
}
