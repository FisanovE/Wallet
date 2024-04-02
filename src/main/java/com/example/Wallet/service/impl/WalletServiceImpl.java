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
import jakarta.persistence.LockModeType;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class WalletServiceImpl implements WalletService {

    private final WalletRepository walletRepository;
    private final AccountMapper accountMapper;

    /**
     * Обновляет информацию о состоянии счёта по заданному идентификатору.
     *
     * @param accountDtoIn объект AccountDtoIn с обновленными данными счёта
     * @return объект AccountDtoOut с информацией о состоянии счёта после операции
     * @throws @throws NotFoundException если категория не найдена
     * @throws UncorrectedParametersException если сумма средств на счету становится отрицательной после списания
     */
    @Lock(LockModeType.PESSIMISTIC_WRITE)
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
        return accountMapper.toAccountDtoOut(walletRepository.save(account));
    }

    /**
     * Возвращает состояние счёта по заданному идентификатору.
     *
     * @param id идентификатор счёта
     * @return объект AccountDtoOut
     * @throws NotFoundException если категория не найдена
     */
    @Lock(LockModeType.PESSIMISTIC_READ)
    @Override
    public AccountDtoOut getById(Long id) {
        Account account = walletRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Account by " + id + " not found"));
        return accountMapper.toAccountDtoOut(account);
    }
}
