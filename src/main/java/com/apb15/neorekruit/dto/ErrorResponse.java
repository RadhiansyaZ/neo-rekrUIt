package com.apb15.neorekruit.dto;

import lombok.Builder;

@Builder
public class ErrorResponse {
    private final String errorMsg;

    @Override
    public String toString() {
        return String.format("\"error\": \"%s\"", this.errorMsg);
    }
}
