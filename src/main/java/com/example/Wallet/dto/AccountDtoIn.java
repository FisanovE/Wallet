package com.example.Wallet.dto;

import com.example.Wallet.model.OperationType;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.Positive;

@Getter
@Setter
@AllArgsConstructor

public class AccountDtoIn {

    @NotNull(message = "ID cannot be null")
    private Long walletId;

    @NotNull(message = "Operation type cannot be null")
    private OperationType operationType;

    @NotNull(message = "Amount value cannot be null")
    @Positive(message = "Amount value can be positive")
    private int amount;
}
