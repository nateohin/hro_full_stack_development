//package nl.caladus.hro.service;
//
//import nl.caladus.hro.model.Account;
//import nl.caladus.hro.model.AccountHolder;
//import nl.caladus.hro.repository.AccountRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//
//import java.util.HashSet;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.mockito.Mockito.when;
//
//@ExtendWith(MockitoExtension.class)
//@DataJpaTest
//class AccountServiceTest {
//
//    private static final String IBAN1 = "1234";
//    private static final String IBAN2 = "4321";
//    private static final float INITIAL_AMOUNT1 = 10000F;
//    private static final float INITIAL_AMOUNT2 = 40000F;
//
//    @Mock
//    private AccountRepository accountRepository;
//
//    @InjectMocks
//    private AccountService accountService;
//
//    private Account account;
//
//    @BeforeEach
//    void setUp() {
//
//        AccountHolder accountHolder1 = new AccountHolder();
//        accountHolder1.setFirstName("Daniel");
//        accountHolder1.setLastName("Kaluuya");
//        AccountHolder accountHolder2 = new AccountHolder();
//        accountHolder2.setFirstName("Georgina");
//        accountHolder2.setLastName("Campbell");
//        HashSet<AccountHolder> accountHolders = new HashSet<>();
//        accountHolders.add(accountHolder1);
//        accountHolders.add(accountHolder2);
//        account = new Account();
//        account.setIBAN(IBAN1);
//        account.setAmount(INITIAL_AMOUNT2);
//        account.setAccountHolders(accountHolders);
//    }
//
//    @Test
//    void createAccount() {
//
//        // Given
//        when(accountRepository.findByIBAN(IBAN1).getAmount()).thenReturn(1000F);
//
////        // When
//        accountService.addAccount(account);
//
//        // Then
//        assertThat(accountService).isNotNull();
//        System.out.println(accountService.getAccounts());
////        assertThat(accountService.getAccountByIBAN(IBAN1).getAmount()).isEqualTo(INITIAL_AMOUNT1);
//    }
//
////    @Test
////    void updateAccount() {
////
////        // Given
////        when(accountService.getAccount(account.getIBAN())).thenReturn(account);
////
////        // When
////        account.setAmount(1000F);
////        account.getAccountHolders().get(0).setFirstName("Tom");
////        accountService.updateAccount( account);
////
////        // Then
////        assertThat(accountService).isNotNull();
////        assertThat(accountService.getAccount("1234567890").getAmount()).isEqualTo(1000);
////        assertThat(accountService.getAccount("1234567890").getAccountHolders().get(0).getFirstName()).isEqualTo("Tom");
////
////    }
////
////    @Test
////    void deleteAccount() {
////
////        // Given
////        assertThat(accountService).isNotNull();
////
////        // When
////        accountService.deleteAccount(account.getIBAN());
////
////        // Then
////        assertThat(accountService.getAccount("")).isNull();
////    }
////
////    @Test
////    void deleteAccountByIBAN() {
////
////        // Given
////        assertThat(accountService).isNotNull();
////
////        // When
////        accountService.deleteAccount(account.getIBAN());
////
////        // Then
////        assertThat(accountService.getAccount("")).isNull();
////    }
//
//}
