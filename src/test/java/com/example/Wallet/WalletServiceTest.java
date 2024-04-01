package com.example.Wallet;

import com.example.Wallet.dto.AccountDtoIn;
import com.example.Wallet.dto.AccountDtoOut;
import com.example.Wallet.exeptions.NotFoundException;
import com.example.Wallet.exeptions.UncorrectedParametersException;
import com.example.Wallet.mappers.AccountMapper;
import com.example.Wallet.model.Account;
import com.example.Wallet.model.OperationType;
import com.example.Wallet.repository.WalletRepository;
import com.example.Wallet.service.impl.WalletServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class WalletServiceTest {
    @Mock
    private WalletRepository walletRepository;

    @Mock
    private AccountMapper accountMapper;

    @InjectMocks
    private WalletServiceImpl walletService;

    @Test
    void save_whenAccountValid_thenSavedAccount() {
        Long walletId = 1L;
        OperationType operationType = OperationType.DEPOSIT;
        int amount = 1000;
        AccountDtoIn accountDtoIn = new AccountDtoIn(walletId, operationType, amount);
        Account account = new Account(walletId, amount);
        Account accountUpdated = new Account(walletId, amount + amount);
        AccountDtoOut accountDtoOut = new AccountDtoOut(walletId, amount + amount);

        when(walletRepository.findById(walletId)).thenReturn(Optional.of(account));
        when(walletRepository.save(any(Account.class))).thenReturn(accountUpdated);
        when(accountMapper.toAccountDtoOut(any(Account.class))).thenReturn(accountDtoOut);

        AccountDtoOut actualAccountDtoOut = walletService.save(accountDtoIn);

        assertEquals(accountDtoOut, actualAccountDtoOut);
    }

    @Test
    void save_whenAmountDifferenceNotPositive_thenReturnException() {
        Long walletId = 1L;
        OperationType operationType = OperationType.WITHDRAW;
        int amount = 2000;
        AccountDtoIn accountDtoIn = new AccountDtoIn(walletId, operationType, amount);
        Account account = new Account(walletId, 1000);
        Account accountUpdated = new Account(walletId, amount + amount);

        when(walletRepository.findById(walletId)).thenReturn(Optional.of(account));

        assertThrows(UncorrectedParametersException.class,
                () -> walletService.save(accountDtoIn));

        verify(walletRepository, never()).save(accountUpdated);

    }

    @Test
    void save_whenWalletIdNotValid_thenReturnException() {
        Long walletId = 1L;
        OperationType operationType = OperationType.WITHDRAW;
        int amount = 1000;
        AccountDtoIn accountDtoIn = new AccountDtoIn(2L, operationType, amount);
        Account accountUpdated = new Account(walletId, amount);

        when(walletRepository.findById(2L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class,
                () -> walletService.save(accountDtoIn));

        verify(walletRepository, never()).save(accountUpdated);
    }

    @Test
    void getById_whenWalletIdNotValid_thenReturnException() {
        Long walletId = 2L;

        when(walletRepository.findById(2L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class,
                () -> walletService.getById(walletId));
    }
}
