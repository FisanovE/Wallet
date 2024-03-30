package com.example.Wallet.service.impl;

import com.example.Wallet.dto.AccountDtoIn;
import com.example.Wallet.dto.AccountDtoOut;
import com.example.Wallet.exeptions.NotFoundException;
import com.example.Wallet.exeptions.UncorrectedParametersException;
import com.example.Wallet.mappers.AccountMapper;
import com.example.Wallet.model.Account;
import com.example.Wallet.model.OperationType;
import com.example.Wallet.repository.WalletRepository;
import com.example.Wallet.service.WalletService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class WalletServiceImpl implements WalletService {

    private final WalletRepository walletRepository;

    @Override
    public AccountDtoOut save(AccountDtoIn accountDtoIn) {
        Account account = walletRepository.findById(accountDtoIn.getWalletId()).orElseThrow(
                () -> new NotFoundException("Account by " + accountDtoIn.getWalletId() + " not found"));
        if (accountDtoIn.getOperationType().equals(OperationType.WITHDRAW) && account.getAmount() - accountDtoIn.getAmount() < 0) {
            throw new UncorrectedParametersException("Not enough funds to complete the operation");
        }
        if (accountDtoIn.getOperationType().equals(OperationType.DEPOSIT)) {
            account.setAmount(account.getAmount() + accountDtoIn.getAmount());
        } else {
            account.setAmount(account.getAmount() - accountDtoIn.getAmount());
        }
        return AccountMapper.toAccountDtoOut(walletRepository.save(account));
    }

    @Override
    public AccountDtoOut getById(Long id) {
        Account account = walletRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Account by " + id + " not found"));
        return AccountMapper.toAccountDtoOut(account);
    }
}
