package com.example.Wallet.service;

import com.example.Wallet.dto.AccountDtoIn;
import com.example.Wallet.dto.AccountDtoOut;

/**
 * Интерфейс WalletService, определяющий методы для работы с состоянием счёта
 */
public interface WalletService {
    AccountDtoOut save(AccountDtoIn accountDtoIn);

    AccountDtoOut getById(Long id);
}
