package com.equityrisk.platform.repository;


import com.equityrisk.platform.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {


}
