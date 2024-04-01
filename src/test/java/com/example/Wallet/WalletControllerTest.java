package com.example.Wallet;

import com.example.Wallet.controllers.WalletController;
import com.example.Wallet.dto.AccountDtoIn;
import com.example.Wallet.dto.AccountDtoOut;
import com.example.Wallet.model.OperationType;
import com.example.Wallet.service.WalletService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(WalletController.class)
public class WalletControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    WalletService walletService;
    AccountDtoIn accountDtoIn;
    AccountDtoOut accountDtoOut;
    Long id;
    OperationType operationType;
    int amount;

    @BeforeEach
    void setUp() {
        id = 1L;
        operationType = OperationType.DEPOSIT;
        amount = 1000;
        accountDtoIn = new AccountDtoIn(id, operationType, amount);
        accountDtoOut = new AccountDtoOut(id, amount);
    }

    @Test
    void save_whenAccountDtoInValid_thenStatus200andAccountSavedAndReturned() throws Exception {
        when(walletService.save(any(AccountDtoIn.class))).thenReturn(accountDtoOut);

        mockMvc.perform(post("/api/v1/wallet")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(accountDtoIn)))
                .andExpectAll(
                        status().isOk(),
                        jsonPath("$.walletId", Matchers.is(1)),
                        jsonPath("$.amount", Matchers.is(amount)));
    }

    @Test
    void getById_whenExistsAccountId_thenStatus200andAccountReturned() throws Exception {
        when(walletService.getById(anyLong())).thenReturn(accountDtoOut);

        mockMvc.perform(get("/api/v1/wallets/{id}", id)
                        .content(objectMapper.writeValueAsString(accountDtoIn)))
                .andExpectAll(
                        status().isOk(),
                        jsonPath("$.walletId", Matchers.is(1)),
                        jsonPath("$.amount", Matchers.is(amount)));
    }
}
