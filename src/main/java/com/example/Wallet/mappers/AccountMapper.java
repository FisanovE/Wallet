package com.example.Wallet.mappers;

import com.example.Wallet.dto.AccountDtoOut;
import com.example.Wallet.model.Account;
import lombok.experimental.UtilityClass;

@UtilityClass
public class AccountMapper {

    public AccountDtoOut toAccountDtoOut(Account account) {
        AccountDtoOut accountDtoOut = new AccountDtoOut();
        accountDtoOut.setWalletId(account.getWalletId());
        accountDtoOut.setAmount(account.getAmount());
        return accountDtoOut;
    }
}
