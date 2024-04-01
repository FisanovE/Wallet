package com.example.Wallet.repository;

import com.example.Wallet.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Интерфейс WalletRepository, представляющий репозиторий для работы с сущностью Account.
 * Расширяет JpaRepository для наследования базовых методов работы с базой данных.
 */
public interface WalletRepository extends JpaRepository<Account, Long> {

}
