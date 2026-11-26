package com.equityrisk.platform.dto;

public record ApiErrorResponse(
        int status,
        String error,
        String message
) {
}
