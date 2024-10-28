package com.technicaltest.bcnc.mapper;

import com.technicaltest.bcnc.entity.Price;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZoneOffset;

public class PriceRowMapper implements RowMapper<Price> {

    public static final String PRODUCT_ID = "PRODUCT_ID";
    public static final String BRAND_ID = "BRAND_ID";
    public static final String START_DATE = "START_DATE";
    public static final String END_DATE = "END_DATE";
    public static final String PRICE_LIST = "PRICE_LIST";
    public static final String PRICE = "PRICE";
    public static final String CURRENCY = "CURRENCY";

    @Override
    public Price mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Price.builder()
                .productId(rs.getInt(PRODUCT_ID))
                .brandId(rs.getInt(BRAND_ID))
                .startDate(rs.getTimestamp(START_DATE).toInstant().atOffset(ZoneOffset.UTC))
                .endDate(rs.getTimestamp(END_DATE).toInstant().atOffset(ZoneOffset.UTC))
                .priceList(rs.getInt(PRICE_LIST))
                .productPrice(rs.getDouble(PRICE))
                .currency(rs.getString(CURRENCY))
                .build();
    }
}