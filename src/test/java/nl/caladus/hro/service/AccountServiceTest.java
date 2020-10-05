package nl.caladus.hro.service;

import nl.caladus.hro.model.Account;
import nl.caladus.hro.model.AccountHolder;
import nl.caladus.hro.repository.AccountRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {

    public static final String IBAN = "1234567890";
    @Mock
    private AccountRepository accountRepository;
    @InjectMocks
    private AccountServiceTestImpl accountServiceTest;

    @Test
    void readAccount() {

        // Given
        AccountHolder accountHolder1 = new AccountHolder("John", "Doe");
        AccountHolder accountHolder2 = new AccountHolder("Jane", "Doe");
        Account account = new Account(IBAN, 1000F, Arrays.asList(accountHolder1, accountHolder2));
        when(accountRepository.getAccount(eq(IBAN))).thenReturn(account);

        // When
        accountServiceTest.addAccount(account);

        // Then
        assertThat(accountServiceTest.getAccount(IBAN)).isEqualTo(account);
        verify(accountRepository, times(1)).addAccount(account);
    }

    @Test
    void updateAccount() {

        // Given
        AccountHolder accountHolder1 = new AccountHolder("John", "Doe");
        AccountHolder accountHolder2 = new AccountHolder("Jane", "Doe");
        Account account = new Account(IBAN, 1000F, Arrays.asList(accountHolder1, accountHolder2));
        when(accountRepository.getAccount(eq(IBAN))).thenReturn(account);

        // When
        account.setBlocked(true);
        accountServiceTest.updateAccount(account);

        // Then
        assertThat(accountServiceTest.getAccount(IBAN).isBlocked()).isTrue();
        verify(accountRepository, times(1)).updateAccount(account);
    }

    @Test
    void deleteAccount() {

        // Given
        AccountHolder accountHolder1 = new AccountHolder("John", "Doe");
        AccountHolder accountHolder2 = new AccountHolder("Jane", "Doe");
        Account account = new Account(IBAN, 1000F, Arrays.asList(accountHolder1, accountHolder2));
        when(accountRepository.getAccount(eq(IBAN))).thenReturn(null);

        // When
        accountServiceTest.addAccount(account);
        accountServiceTest.deleteAccount(IBAN);

        // Then
        assertThat(accountServiceTest.getAccount(IBAN)).isNull();
        verify(accountRepository, times(1)).deleteAccount(IBAN);
    }

    static class AccountServiceTestImpl extends AccountService {
        public AccountServiceTestImpl(AccountRepository accountRepository) {
            super(accountRepository);
        }
    }
}
