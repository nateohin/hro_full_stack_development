//package nl.caladus.hro.service;
//
//import nl.caladus.hro.model.Account;
//import nl.caladus.hro.model.AccountHolder;
//import nl.caladus.hro.repository.AccountHolderRepository;
//import nl.caladus.hro.repository.AccountRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//
//import java.util.ArrayList;
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
//    @Mock
//    private AccountHolderRepository accountHolderRepository;
//
//    @InjectMocks
//    private AccountService accountService;
//
//    @BeforeEach
//    void setUp() {
//
////        accountService.addAccount(account);
//
//        AccountHolder accountHolder3 = new AccountHolder("Daniel", "Kaluuya");
//        AccountHolder accountHolder4 = new AccountHolder("Georgina", "Campbell");
//        HashSet<AccountHolder> accountHolders1 = new HashSet<>();
//        accountHolders1.add(accountHolder3);
//        accountHolders1.add(accountHolder4);
//        Account account1 = new Account(IBAN2, INITIAL_AMOUNT2, accountHolders1);
//
//        ArrayList<Account> accounts = new ArrayList<>();
////        accounts.add(account);
//        accounts.add(account1);
//
//        accountService.saveAll(accounts);
////        accountHolderRepository.saveAll(accountHolders);
//    }
//
//    @Test
//    void createAccount() {
//
//        // Given
//        when(accountRepository.findByIBAN(IBAN1).getAmount()).thenReturn(1000F);
//
////        // When
//        AccountHolder accountHolder = new AccountHolder("John", "Doe");
//        accountHolder.setId(1L);
//        AccountHolder accountHolder1 = new AccountHolder("Jane", "Doe");
//        accountHolder1.setId(2L);
//        HashSet<AccountHolder> accountHolders = new HashSet<>();
//        accountHolders.add(accountHolder);
//        accountHolders.add(accountHolder1);
//        Account account = new Account(IBAN1, INITIAL_AMOUNT1, accountHolders);
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
