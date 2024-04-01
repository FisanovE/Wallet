package com.example.Wallet;

import com.example.Wallet.model.Account;
import com.example.Wallet.repository.WalletRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * TODO доработать загрузку базы для тестов через liquibase, когда будет время
 */
@DataJpaTest
//@ImportAutoConfiguration(LiquibaseAutoConfiguration.class)
/*@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource(properties = {
        "spring.datasource.url=jdbc:tc:postgresql:14-alpine:///payment"
})*/
public class WalletRepositoryTest {

    @Autowired
    private WalletRepository walletRepository;

    private final Long walletId = 1L;
    private final int amount = 1000;
    private final Account account = new Account(walletId, amount);

  /*  @Test
    void save_whenWalletIdIsValid_thenSaveAndReturnAccount() {
        Account actualAccount = walletRepository.save(account);

        assertNotNull(actualAccount);
        assertEquals(actualAccount.getWalletId(), walletId);
        assertEquals(actualAccount.getAmount(), amount);
    }

    @Test
    void findById_whenWalletIdIsValid_thenReturnAccount() {
        Optional<Account> actualAccount = walletRepository.findById(walletId);

        assertTrue(actualAccount.isPresent());
    }*/
}
