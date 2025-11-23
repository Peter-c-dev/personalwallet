package com.wallet.personalwallet.repository;

import com.wallet.personalwallet.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {}
