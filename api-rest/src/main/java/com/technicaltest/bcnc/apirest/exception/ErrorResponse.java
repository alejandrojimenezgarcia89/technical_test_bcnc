package com.technicaltest.bcnc.apirest.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.OffsetDateTime;

@AllArgsConstructor
@Data
public class ErrorResponse {
    private int statusCode;
    private OffsetDateTime timestamp;
    private String message;
    private String description;
}
