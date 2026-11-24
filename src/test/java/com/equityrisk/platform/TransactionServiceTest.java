package com.equityrisk.platform;

import com.equityrisk.platform.dto.TransactionRequest;
import com.equityrisk.platform.dto.TransactionResponse;
import com.equityrisk.platform.service.TransactionService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class TransactionServiceTest {

    private final TransactionService transactionService =
            new TransactionService();

    @Test
    @DisplayName("Should create a transaction response")
    void shouldCreateTransactionResponse() {

        TransactionRequest request = new TransactionRequest(
                15L,
                new BigDecimal("5000.00"),
                "BRL"
        );

        TransactionResponse response =
                transactionService.createTransaction(request);

        Assertions.assertEquals(1L, response.id());
        Assertions.assertEquals(15L, response.customerId());
        Assertions.assertEquals(
                new BigDecimal("5000.00"),
                response.amount()
        );
        Assertions.assertEquals("BRL", response.currency());
        Assertions.assertEquals("PENDING", response.status());
    }
}
