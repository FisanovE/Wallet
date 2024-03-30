package com.example.Wallet.controllers;

import com.example.Wallet.dto.AccountDtoIn;
import com.example.Wallet.dto.AccountDtoOut;
import com.example.Wallet.service.WalletService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
@Slf4j
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1")
public class WalletController {

    private final WalletService walletService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/wallet")
    public AccountDtoOut save(@Valid @RequestBody AccountDtoIn accountDtoIn) {
        log.info("POST account");
        return walletService.save(accountDtoIn);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/wallets/{id}")
    public AccountDtoOut getById(@PathVariable Long id) {
        log.info("GET account by ID: {}", id);
        return walletService.getById(id);
    }
}
