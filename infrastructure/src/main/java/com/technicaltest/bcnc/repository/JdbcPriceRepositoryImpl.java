package com.technicaltest.bcnc.repository;

import com.technicaltest.bcnc.entity.Price;
import com.technicaltest.bcnc.exception.PriceNotFoundException;
import com.technicaltest.bcnc.mapper.PriceRowMapper;
import com.technicaltest.bcnc.util.FileUtils;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.time.OffsetDateTime;

@Repository
@RequiredArgsConstructor
public class JdbcPriceRepositoryImpl implements JdbcPriceRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final FileUtils fileUtils;

    private String findHighestPriorityPriceSql;

    @PostConstruct
    public void init() throws IOException {
        findHighestPriorityPriceSql = fileUtils.readSql("getHighestPriorityPrice.sql");
    }

    @Override
    public Price findHighestPriorityPrice(long productId, int brandId, OffsetDateTime applicationDate) {
        try {
            var params = getMapSqlParameterSource(productId, brandId, applicationDate);
            return namedParameterJdbcTemplate.queryForObject(findHighestPriorityPriceSql, params, new PriceRowMapper());
        } catch (IncorrectResultSizeDataAccessException e) {
            throw new PriceNotFoundException(productId, brandId, applicationDate);
        }
    }

    private MapSqlParameterSource getMapSqlParameterSource(long productId, int brandId, OffsetDateTime applicationDate) {
        var params = new MapSqlParameterSource();
        params.addValue("PRODUCT_ID", productId);
        params.addValue("BRAND_ID", brandId);
        params.addValue("APPLICATION_DATE", applicationDate);
        return params;
    }
}
