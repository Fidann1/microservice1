package com.example.employee_service.dto;

import lombok.Builder;

import java.util.List;

@Builder
public class ErrorResponse {
    private String message;
    private Integer status;
    List<String> errors;
}
