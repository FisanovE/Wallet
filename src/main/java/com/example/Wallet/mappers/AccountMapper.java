package com.example.Wallet.mappers;

import com.example.Wallet.dto.AccountDtoOut;
import com.example.Wallet.model.Account;
import org.springframework.stereotype.Component;

/**
 * Класс AccountMapper служит для преобразования объектов Account и связанных с ними DTO.
 */
@Component
public class AccountMapper {

    /**
     * Преобразует объект Account в объект AccountDtoOut.
     *
     * @param account объект Account для преобразования
     * @return объект AccountDtoOut, полученный в результате преобразования
     */
    public AccountDtoOut toAccountDtoOut(Account account) {
        AccountDtoOut accountDtoOut = new AccountDtoOut();
        accountDtoOut.setWalletId(account.getWalletId());
        accountDtoOut.setAmount(account.getAmount());
        return accountDtoOut;
    }
}
