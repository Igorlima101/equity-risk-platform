package com.equityrisk.platform.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long customerId;

    private BigDecimal amount;

    private String currency;

    @Enumerated(EnumType.STRING)
    private TransactionStatus status;

    protected Transaction() {
    }

    public Transaction(Long customerId, BigDecimal amount, String currency){
        this.customerId = customerId;
        this.amount = amount;
        this.currency = currency;
        this.status = TransactionStatus.PENDING;
    }

    public Long getId() {
        return id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }

    public TransactionStatus getStatus() {
        return status;
    }

    private void ensurePendingStatus() {
        if (this.status != TransactionStatus.PENDING) {
            throw new IllegalStateException(
                    "Only pending transactions can change status"
            );
        }
    }

    public void approve() {
        ensurePendingStatus();
        this.status = TransactionStatus.APPROVED;
    }

    public void reject() {
        ensurePendingStatus();
        this.status = TransactionStatus.REJECTED;
    }
}
