package com.equityrisk.platform;


import com.equityrisk.platform.dto.TransactionRequest;
import com.equityrisk.platform.dto.TransactionResponse;
import com.equityrisk.platform.entity.Transaction;
import com.equityrisk.platform.repository.TransactionRepository;
import com.equityrisk.platform.service.TransactionService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceTest {

    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private TransactionService transactionService;

    @Test
    void shouldCreateTransactionResponse() {

        TransactionRequest request = new TransactionRequest(
                15L,
                new BigDecimal("5000.00"),
                "BRL"
        );

        when(transactionRepository.save(any(Transaction.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        TransactionResponse response = transactionService.createTransaction(request);

        assertEquals(15L, response.customerId());
        assertEquals(new BigDecimal("5000.00"), response.amount());
        assertEquals("BRL", response.currency());
        assertEquals("PENDING", response.status());

        verify(transactionRepository).save(any(Transaction.class));
    }
}
