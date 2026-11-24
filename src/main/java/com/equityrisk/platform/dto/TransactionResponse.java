package com.equityrisk.platform.dto;

import java.math.BigDecimal;

public record TransactionResponse(Long id, Long customerId, BigDecimal amount, String currency, String status) {
}
