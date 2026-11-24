package com.equityrisk.platform.controller;

import com.equityrisk.platform.dto.TransactionRequest;
import com.equityrisk.platform.dto.TransactionResponse;
import com.equityrisk.platform.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    public TransactionResponse createTransaction(@Valid @RequestBody TransactionRequest request){
        return transactionService.createTransaction(request);
    }

}
