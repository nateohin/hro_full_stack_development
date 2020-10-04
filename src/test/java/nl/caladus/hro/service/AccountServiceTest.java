package nl.caladus.hro.service;

import nl.caladus.hro.model.Account;
import nl.caladus.hro.model.AccountHolder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {

    @Mock
    AccountService accountService;

    private Account account;

    @BeforeEach
    void setUp() {
        AccountHolder accountHolder1 = new AccountHolder("John", "Doe");
        AccountHolder accountHolder2 = new AccountHolder("Jane", "Doe");
        account = new Account("1234567890", 1000F, Arrays.asList(accountHolder1, accountHolder2));
    }

    @Test
    void createAccount() {

        // Given
        when(accountService.getAccount(account.getIBAN())).thenReturn(account);

        // When
        accountService.addAccount( account);

        // Then
        assertThat(accountService).isNotNull();
        assertThat(accountService.getAccount("1234567890").getIBAN()).isEqualTo(account.getIBAN());
        assertThat(accountService.getAccount("1234567890").getAccountHolders()).isEqualTo(account.getAccountHolders());

    }

//    @Test
//    void failToCreateAccount() {
//        assertThrows(NullPointerException.class, () ->
//                accountService.addAccount( null));
//    }

    @Test
    void updateAccount() {

        // Given
        when(accountService.getAccount(account.getIBAN())).thenReturn(account);

        // When
        account.setAmount(1000F);
        account.getAccountHolders().get(0).setFirstName("Tom");
        accountService.updateAccount( account);

        // Then
        assertThat(accountService).isNotNull();
        assertThat(accountService.getAccount("1234567890").getAmount()).isEqualTo(1000);
        assertThat(accountService.getAccount("1234567890").getAccountHolders().get(0).getFirstName()).isEqualTo("Tom");

    }

    @Test
    void deleteAccount() {

        // Given
        assertThat(accountService).isNotNull();

        // When
        accountService.deleteAccount(account.getIBAN());

        // Then
        assertThat(accountService.getAccount("")).isNull();
    }

    @Test
    void deleteAccountByIBAN() {

        // Given
        assertThat(accountService).isNotNull();

        // When
        accountService.deleteAccount(account.getIBAN());

        // Then
        assertThat(accountService.getAccount("")).isNull();
    }

}
