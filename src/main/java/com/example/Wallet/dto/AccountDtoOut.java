package com.example.Wallet.dto;

import com.example.Wallet.model.OperationType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountDtoOut {
    private Long walletId;
    private int amount;
}
