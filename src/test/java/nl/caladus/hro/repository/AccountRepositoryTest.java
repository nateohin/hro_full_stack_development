package nl.caladus.hro.repository;

import nl.caladus.hro.model.Account;
import nl.caladus.hro.model.AccountHolder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class AccountRepositoryTest {

    @InjectMocks
    private AccountRepository accountRepository;

    @Test
    public void Test() {

        // Given
        AccountHolder accountHolder1 = new AccountHolder("John", "Doe");
        AccountHolder accountHolder2 = new AccountHolder("Jane", "Doe");
        Account account = new Account("1234567890", 1000F, Arrays.asList(accountHolder1, accountHolder2));

        // When
        accountRepository.getAccounts().put(account.getIBAN(), account);

        // Then
        assertThat(accountRepository).isNotNull();
    }

}
