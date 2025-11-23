package com.wallet.personalwallet.controller;

import com.wallet.personalwallet.model.Transaction;
import com.wallet.personalwallet.model.Wallet;
import com.wallet.personalwallet.repository.WalletRepository;
import com.wallet.personalwallet.repository.TransactionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/wallet")
public class WalletController {

    @Autowired
    private WalletRepository walletRepo;

    @Autowired
    private TransactionRepository transactionRepo;

    @PostMapping("/deposit/{walletId}")
    @Transactional
    public Wallet deposit(@PathVariable Long walletId, @RequestParam double amount) {

        Wallet wallet = walletRepo.findById(walletId)
                .orElseThrow(() -> new RuntimeException("Wallet not found"));

        wallet.setBalance(wallet.getBalance() + amount);

        Transaction t = new Transaction(null, amount, "CREDIT",
                "Deposit", LocalDateTime.now(), wallet);

        transactionRepo.save(t);

        return walletRepo.save(wallet);
    }

    @PostMapping("/withdraw/{walletId}")
    @Transactional
    public Wallet withdraw(@PathVariable Long walletId, @RequestParam double amount) {

        Wallet wallet = walletRepo.findById(walletId)
                .orElseThrow(() -> new RuntimeException("Wallet not found"));

        if (wallet.getBalance() < amount)
            throw new RuntimeException("Insufficient funds");

        wallet.setBalance(wallet.getBalance() - amount);

        Transaction t = new Transaction(null, amount, "DEBIT",
                "Withdrawal", LocalDateTime.now(), wallet);

        transactionRepo.save(t);

        return walletRepo.save(wallet);
    }

    @GetMapping("/transactions/{walletId}")
    public List<Transaction> getTransactions(@PathVariable Long walletId) {

        Wallet wallet = walletRepo.findById(walletId)
                .orElseThrow(() -> new RuntimeException("Wallet not found"));

        return wallet.getTransactions();
    }
}
