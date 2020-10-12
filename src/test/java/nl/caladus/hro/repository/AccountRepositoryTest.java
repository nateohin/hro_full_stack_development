package nl.caladus.hro.repository;

import nl.caladus.hro.model.Account;
import nl.caladus.hro.model.AccountHolder;
import nl.caladus.hro.model.Address;
import nl.caladus.hro.model.Gender;
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

    private Account account;
    private Account account1;
    private HashSet<AccountHolder> accountHolders;


    @BeforeEach
    void setUp() {

        AccountHolder accountHolder = new AccountHolder();
        accountHolder.setFirstName("John");
        accountHolder.setLastName("Doe");
        accountHolder.setId(1L);
        accountHolder.setGender(Gender.MALE);
        Address address = new Address("Rotterdam", "3000AA", "weststraat", "88");
        accountHolder.setAddress(address);
        AccountHolder accountHolder1 = new AccountHolder();
        accountHolder1.setFirstName("Jane");
        accountHolder1.setLastName("Doe");
        accountHolder1.setId(2L);
        accountHolder1.setGender(Gender.FEMALE);
        accountHolder1.setAddress(address);
        accountHolders = new HashSet<>();
        accountHolders.add(accountHolder);
        accountHolders.add(accountHolder1);
        account = new Account();
        account.setIBAN(IBAN1);
        account.setAmount(INITIAL_AMOUNT1);
        account.setAccountHolders(accountHolders);

        AccountHolder accountHolder3 = new AccountHolder();
        accountHolder3.setFirstName("Daniel");
        accountHolder3.setLastName("Kaluuya");
        accountHolder3.setAddress(address);
        accountHolder3.setGender(Gender.MALE);
        AccountHolder accountHolder4 = new AccountHolder();
        accountHolder4.setFirstName("Georgina");
        accountHolder4.setLastName("Campbell");
        accountHolder4.setAddress(address);
        accountHolder4.setGender(Gender.FEMALE);
        HashSet<AccountHolder> accountHolders1 = new HashSet<>();
        accountHolders1.add(accountHolder3);
        accountHolders1.add(accountHolder4);
        account1= new Account();
        account1.setIBAN(IBAN2);
        account1.setAmount(INITIAL_AMOUNT2);
        account1.setAccountHolders(accountHolders1);
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
        AccountHolder accountHolder = new AccountHolder();
        accountHolder.setFirstName("John");
        accountHolder.setLastName("Doe");
        account.getAccountHolders().add(accountHolder);
        accountRepository.save(account);

        // Then
        Account account1 = accountRepository.findByIBAN(IBAN1);
        assertThat(account1.getAmount()).isEqualTo(9500F);
        assertThat(account1.getAccountHolders().size()).isEqualTo(3);
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
