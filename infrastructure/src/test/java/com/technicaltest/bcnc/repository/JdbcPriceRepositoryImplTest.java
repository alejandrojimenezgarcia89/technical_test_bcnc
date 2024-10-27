package com.technicaltest.bcnc.repository;

import com.technicaltest.bcnc.entity.Price;
import com.technicaltest.bcnc.exception.PriceNotFoundException;
import com.technicaltest.bcnc.mapper.PriceRowMapper;
import com.technicaltest.bcnc.util.FileUtils;
import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.io.IOException;
import java.time.OffsetDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class JdbcPriceRepositoryImplTest {

    @Mock
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Mock
    private FileUtils fileUtils;

    @InjectMocks
    private JdbcPriceRepositoryImpl jdbcPriceRepository;

    @BeforeEach
    void setUp() throws IOException {
        when(fileUtils.readSql(anyString())).thenReturn("SELECT * FROM prices WHERE ...");
        jdbcPriceRepository.init();
    }

    @Test
    void testFindHighestPriorityPrice_Success() {
        Price expectedPrice = Instancio.create(Price.class);
        when(namedParameterJdbcTemplate.queryForObject(anyString(), any(MapSqlParameterSource.class), any(PriceRowMapper.class)))
                .thenReturn(expectedPrice);

        Price result = jdbcPriceRepository.findHighestPriorityPrice(1L, 1, OffsetDateTime.now());

        assertNotNull(result);
        assertEquals(expectedPrice, result);
    }

    @Test
    void testFindHighestPriorityPrice_NotFound() {
        when(namedParameterJdbcTemplate.queryForObject(anyString(), any(MapSqlParameterSource.class), any(PriceRowMapper.class)))
                .thenThrow(new IncorrectResultSizeDataAccessException(1));
        Executable notFoundExecution = () -> jdbcPriceRepository.findHighestPriorityPrice(1L, 1, OffsetDateTime.now());
        assertThrows(PriceNotFoundException.class, notFoundExecution);
    }
}