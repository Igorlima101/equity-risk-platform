package com.equityrisk.platform.controller;

import com.equityrisk.platform.dto.TransactionRequest;
import com.equityrisk.platform.dto.TransactionResponse;
import com.equityrisk.platform.service.TransactionService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.data.web.PagedModel;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public TransactionResponse createTransaction(@Valid @RequestBody TransactionRequest request){
        return transactionService.createTransaction(request);
    }

    @GetMapping("/{id}")
    public TransactionResponse getTransactionById(@PathVariable Long id) {
        return transactionService.getTransactionById(id);
    }

    @GetMapping
    public PagedModel<TransactionResponse> getAllTransactions(
            @RequestParam(defaultValue = "0") @Min(0) int page,
            @RequestParam(defaultValue = "10") @Min(1) @Max(100) int size) {

        Pageable pageable = PageRequest.of(
                page,
                size,
                Sort.by("id").descending()
        );

        return new PagedModel<>(
                transactionService.getAllTransactions(pageable)
        );
    }

}
