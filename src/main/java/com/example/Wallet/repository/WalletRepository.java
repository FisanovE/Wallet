package com.example.Wallet.repository;

import com.example.Wallet.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletRepository extends JpaRepository<Account, Long> {

}
