package com.equityrisk.platform.service;

import com.equityrisk.platform.dto.TransactionRequest;
import com.equityrisk.platform.dto.TransactionResponse;
import com.equityrisk.platform.entity.Transaction;
import com.equityrisk.platform.exception.TransactionNotFoundException;
import com.equityrisk.platform.repository.TransactionRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;



@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Transactional
    public TransactionResponse createTransaction(TransactionRequest request) {

        Transaction transaction = new Transaction(
                request.customerId(),
                request.amount(),
                request.currency()
        );

        Transaction savedTransaction = transactionRepository.save(transaction);

        return new TransactionResponse(
                savedTransaction.getId(),
                savedTransaction.getCustomerId(),
                savedTransaction.getAmount(),
                savedTransaction.getCurrency(),
                savedTransaction.getStatus().name()
        );
    }

    public TransactionResponse getTransactionById(Long id){
        Transaction transaction = transactionRepository.findById(id).orElseThrow(() -> new TransactionNotFoundException(id));

        return new TransactionResponse(
                transaction.getId(),
                transaction.getCustomerId(),
                transaction.getAmount(),
                transaction.getCurrency(),
                transaction.getStatus().name()
        );
    }

    public Page<TransactionResponse> getAllTransactions(Pageable pageable) {

        Page<Transaction> transactions = transactionRepository.findAll(pageable);

        return transactions.map(transaction -> new TransactionResponse(
                transaction.getId(),
                transaction.getCustomerId(),
                transaction.getAmount(),
                transaction.getCurrency(),
                transaction.getStatus().name()
        ));
    }
}
