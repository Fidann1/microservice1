package com.example.ms_security.dto;

import lombok.Builder;

import java.util.ArrayList;

@Builder
public class ErrorResponse {
    private String message;
    private Integer code;
    private ArrayList<String> errors;
}
