package com.technicaltest.bcnc.mapper;

import com.technicaltest.bcnc.entity.Price;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PriceRowMapperTest {

    @InjectMocks
    private PriceRowMapper priceRowMapper;

    @Test
    void testMapRow() throws SQLException {
        var productId =1;
        var brandId =1;
        var priceList =1;
        var now = OffsetDateTime.now().withOffsetSameInstant(ZoneOffset.UTC);
        var productPrice =100.0;
        var currency ="EUR";

        ResultSet resultSet = mock(ResultSet.class);
        when(resultSet.getInt(PriceRowMapper.PRODUCT_ID)).thenReturn(productId);
        when(resultSet.getInt(PriceRowMapper.BRAND_ID)).thenReturn(brandId);
        when(resultSet.getTimestamp(PriceRowMapper.START_DATE)).thenReturn(Timestamp.from(now.toInstant()));
        when(resultSet.getTimestamp(PriceRowMapper.END_DATE)).thenReturn(Timestamp.from(now.plusDays(1).toInstant()));
        when(resultSet.getInt(PriceRowMapper.PRICE_LIST)).thenReturn(priceList);
        when(resultSet.getDouble(PriceRowMapper.PRICE)).thenReturn(productPrice);
        when(resultSet.getString(PriceRowMapper.CURRENCY)).thenReturn(currency);


        Price price = priceRowMapper.mapRow(resultSet, 1);

        assertNotNull(price);
        assertEquals(productId, price.getProductId());
        assertEquals(brandId, price.getBrandId());
        assertEquals(priceList, price.getPriceList());
        assertEquals(productPrice, price.getProductPrice());
        assertEquals(currency, price.getCurrency());
        assertEquals(now, price.getStartDate());
        assertEquals(now.plusDays(1), price.getEndDate());
    }
}