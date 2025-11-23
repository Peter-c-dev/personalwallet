package com.wallet.personalwallet.repository;

import com.wallet.personalwallet.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletRepository extends JpaRepository<Wallet, Long> {}
