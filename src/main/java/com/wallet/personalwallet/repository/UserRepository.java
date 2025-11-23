package com.wallet.personalwallet.repository;

import com.wallet.personalwallet.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {}
