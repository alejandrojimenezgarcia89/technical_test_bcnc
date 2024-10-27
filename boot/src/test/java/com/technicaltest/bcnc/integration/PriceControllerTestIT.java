package com.technicaltest.bcnc.integration;

import com.technicaltest.bcnc.exception.PriceNotFoundException;
import com.technicaltest.bcnc.model.PriceRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.text.MessageFormat;
import java.time.OffsetDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
class PriceControllerTestIT {

    public static final String PRICE_URL = "/price";

    public static final String PRODUCT_ID = "productId";
    public static final String BRAND_ID = "brandId";
    public static final String APPLICATION_DATE = "applicationDate";

    @Autowired
    private MockMvc mockMvc;

    @ParameterizedTest
    @CsvSource(value = {
            "2020-06-14T10:00:00Z",
            "2020-06-14T21:00:00Z"
    })
    void testGetPriceWithPriceList1(String dateTimeString) throws Exception {
        mockMvc.perform(get(PRICE_URL)
                        .param(PRODUCT_ID, String.valueOf(35455))
                        .param(BRAND_ID, String.valueOf(1))
                        .param(APPLICATION_DATE, dateTimeString)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.priceList").value(1))
                .andExpect(jsonPath("$.price").value(35.5));
    }
    @Test
    void testGetPriceWithPriceList2() throws Exception {
        mockMvc.perform(get(PRICE_URL)
                        .param(PRODUCT_ID, String.valueOf(35455))
                        .param(BRAND_ID, String.valueOf(1))
                        .param(APPLICATION_DATE, "2020-06-14T16:00:00Z")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.priceList").value(2))
                .andExpect(jsonPath("$.price").value(25.45));
    }
    @Test
    void testGetPriceWithPriceList3() throws Exception {
        mockMvc.perform(get(PRICE_URL)
                        .param(PRODUCT_ID, String.valueOf(35455))
                        .param(BRAND_ID, String.valueOf(1))
                        .param(APPLICATION_DATE, "2020-06-15T10:00:00Z")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.priceList").value(3))
                .andExpect(jsonPath("$.price").value(30.5));
    }
    @Test
    void testGetPriceWithPriceList4() throws Exception {
        mockMvc.perform(get(PRICE_URL)
                        .param(PRODUCT_ID, String.valueOf(35455))
                        .param(BRAND_ID, String.valueOf(1))
                        .param(APPLICATION_DATE, "2020-06-16T21:00:00Z")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.priceList").value(4))
                .andExpect(jsonPath("$.price").value(38.95));
    }

    @Test
    void testHandleIllegalArgumentExceptionProductIdNumberWrongFormat() throws Exception {
        String numberWrongFormat = "not_a_number";
        mockMvc.perform(get(PRICE_URL)
                        .param(PRODUCT_ID, String.valueOf(35455))
                        .param(BRAND_ID, numberWrongFormat)
                        .param(APPLICATION_DATE, "2024-04-24T21:00:00Z")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.statusCode").value(400))
                .andExpect(jsonPath("$.message").value(MessageFormat.format("For input string: \"{0}\"", numberWrongFormat)));
    }
    @Test
    void testHandleIllegalArgumentExceptionProductIdDateWrongFormat() throws Exception {
        String dateWrongFormat = "2024-04-24";
        mockMvc.perform(get(PRICE_URL)
                        .param(PRODUCT_ID, String.valueOf(35455))
                        .param(BRAND_ID, String.valueOf(1))
                        .param(APPLICATION_DATE, dateWrongFormat)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.statusCode").value(400))
                .andExpect(jsonPath("$.message").value(MessageFormat.format("Parse attempt failed for value [{0}]", dateWrongFormat)));
    }

    @Test
    void testMissingParameterRequestValueExceptionProductId() throws Exception {
        mockMvc.perform(get(PRICE_URL)
                        .param(BRAND_ID, String.valueOf(1))
                        .param("application", "2024-04-24T21:00:00Z")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.statusCode").value(400))
                .andExpect(jsonPath("$.message").value("Required parameter 'productId' is not present."));
    }

    @Test
    void testMissingParameterRequestValueExceptionBrandId() throws Exception {
        mockMvc.perform(get(PRICE_URL)
                        .param(PRODUCT_ID, String.valueOf(35455))
                        .param(APPLICATION_DATE, "2024-04-24T21:00:00Z")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.statusCode").value(400))
                .andExpect(jsonPath("$.message").value("Required parameter 'brandId' is not present."));
    }

    @Test
    void testMissingParameterRequestValueExceptionApplicationDate() throws Exception {
        mockMvc.perform(get(PRICE_URL)
                        .param(PRODUCT_ID, String.valueOf(35455))
                        .param(BRAND_ID, String.valueOf(1))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.statusCode").value(400))
                .andExpect(jsonPath("$.message").value("Required parameter 'applicationDate' is not present."));
    }

    @Test
    void testHandlePriceNotFoundException() throws Exception {
        var requestNotFound = new PriceRequest(1, 1, OffsetDateTime.parse("2024-04-24T21:00:00Z"));

        mockMvc.perform(get(PRICE_URL)
                        .param(PRODUCT_ID, String.valueOf(requestNotFound.productId()))
                        .param(BRAND_ID, String.valueOf(requestNotFound.brandId()))
                        .param(APPLICATION_DATE, requestNotFound.applicationDate().toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.statusCode").value(404))
                .andExpect(jsonPath("$.message").value(new PriceNotFoundException(requestNotFound.productId(), requestNotFound.brandId(), requestNotFound.applicationDate()).getMessage()));
    }
}