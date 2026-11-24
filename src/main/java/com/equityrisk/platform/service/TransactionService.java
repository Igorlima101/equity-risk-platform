package com.equityrisk.platform.service;

import com.equityrisk.platform.dto.TransactionRequest;
import com.equityrisk.platform.dto.TransactionResponse;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class TransactionService {

    public TransactionResponse createTransaction(TransactionRequest request) {
      return new TransactionResponse(
             1L,
              request.customerId(),
              request.amount(),
              request.currency(),
              "PENDING"

      );
    }
}
