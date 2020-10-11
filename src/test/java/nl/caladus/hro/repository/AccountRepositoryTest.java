package nl.caladus.hro.repository;

import nl.caladus.hro.model.Account;
import nl.caladus.hro.model.AccountHolder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.HashSet;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class AccountRepositoryTest {

    public static final String IBAN1 = "1234";
    public static final String IBAN2 = "4321";
    private static final float INITIAL_AMOUNT1 = 10000F;
    private static final float INITIAL_AMOUNT2 = 40000F;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    AccountHolderRepository accountHolderRepository;

    private Account account;
    private Account account1;
    private HashSet<AccountHolder> accountHolders;


    @BeforeEach
    void setUp() {

        AccountHolder accountHolder = new AccountHolder("John", "Doe");
        accountHolder.setId(1L);
        AccountHolder accountHolder1 = new AccountHolder("Jane", "Doe");
        accountHolder1.setId(2L);
        accountHolders = new HashSet<>();
        accountHolders.add(accountHolder);
        accountHolders.add(accountHolder1);
        account = new Account(IBAN1, INITIAL_AMOUNT1, accountHolders);

        AccountHolder accountHolder3 = new AccountHolder("Daniel", "Kaluuya");
        AccountHolder accountHolder4 = new AccountHolder("Georgina", "Campbell");
        HashSet<AccountHolder> accountHolders1 = new HashSet<>();
        accountHolders1.add(accountHolder3);
        accountHolders1.add(accountHolder4);
        account1 = new Account(IBAN2, INITIAL_AMOUNT2, accountHolders1);
    }


    @Test
    void readAccountFromRepository() {

        // Given
        accountRepository.save(account);
        accountRepository.save(account1);

        // When
        Account account3 = accountRepository.findByIBAN(IBAN1);
        Account account4 = accountRepository.findByIBAN(IBAN2);

        // Then
        assertThat(account3.getAmount()).isEqualTo(INITIAL_AMOUNT1);
        assertThat(account4.getAmount()).isEqualTo(INITIAL_AMOUNT2);
        assertThat(account3.getAccountHolders()).isEqualTo(accountHolders);
    }

    @Test
    void updateAccountFromRepository() {

        // Given
        accountRepository.save(account);

        // When
        Account account = accountRepository.findByIBAN(IBAN1);
        account.setAmount(account.getAmount() + -500F);
        account.getAccountHolders().remove(0);
        account.getAccountHolders().add(new AccountHolder("Nathan",  "Ernst"));
        accountRepository.save(account);

        // Then
        Account account1 = accountRepository.findByIBAN(IBAN1);
        assertThat(account1.getAmount()).isEqualTo(9500F);
        assertThat(account1.getAccountHolders().size()).isEqualTo(2);
    }

    @Test
    void deleteAccountFromRepository() {

        // Given
        accountRepository.save(account);

        // When
        Account account3 = accountRepository.findByIBAN(IBAN1);
        assertThat(account3).isNotNull();
        accountRepository.deleteByIBAN(account3.getIBAN());
        account3 = accountRepository.findByIBAN(IBAN1);

        // Then
        assertThat(account3).isNull();
    }


}
