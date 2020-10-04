//package nl.caladus.hro.repository;
//
//import nl.caladus.hro.model.Account;
//import nl.caladus.hro.model.AccountHolder;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.util.Arrays;
//import java.util.Map;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.mockito.ArgumentMatchers.anyString;
//import static org.mockito.Mockito.when;
//
//@ExtendWith(MockitoExtension.class)
//public class AccountRepositoryTest {
//
//    @Mock
//    private Map<String, Account> mapData;
//
//    @InjectMocks
//    private AccountRepository accountRepository;
//
//    @Test
//    public void Test() {
//        AccountHolder accountHolder1 = new AccountHolder("John", "Doe");
//        AccountHolder accountHolder2 = new AccountHolder("Jane", "Doe");
//        Account account = new Account("1234567890", 1000F, Arrays.asList(accountHolder1, accountHolder2));
//
//        // Given
//        when(mapData.get(anyString())).thenReturn(new Account());
//
//        // When
//        accountRepository.getAccounts().put(account.getIBAN(), account);
//
//        // Then
//        assertThat(accountRepository).isNotNull();
////        System.out.println(accountRepository.getAccounts());
////        assertThat(accountRepository.getAccounts().get(account.getIBAN()).getAccountHolders().get(0).getFirstName()).isEqualTo("john");
//    }
//
//}
