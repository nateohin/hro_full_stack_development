package nl.caladus.hro.controller;

import nl.caladus.hro.model.Account;
import nl.caladus.hro.model.AccountHolder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("dev")
class AccountControllerTest {

    @LocalServerPort
    private int port;

    private HttpHeaders headers ;
    private Account account;
    private static final String IBAN1 = "1234";
    private static final float INITIAL_AMOUNT1 = 10000F;

    @BeforeEach
    void setUp() {
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        AccountHolder accountHolder = new AccountHolder();
        accountHolder.setFirstName("John");
        accountHolder.setLastName("Doe");
        accountHolder.setId(1L);
        AccountHolder accountHolder1 = new AccountHolder();
        accountHolder1.setId(2L);
        accountHolder1.setFirstName("Jane");
        accountHolder1.setLastName("Doe");
        HashSet<AccountHolder> accountHolders = new HashSet<>();
        accountHolders.add(accountHolder);
        accountHolders.add(accountHolder1);
        account = new Account();
        account.setIBAN(IBAN1);
        account.setAmount(INITIAL_AMOUNT1);
        account.setAccountHolders(accountHolders);
    }

    @AfterEach
    void tearDown() throws URISyntaxException {
        URI uri = new URI("http://localhost:" + port + "/account");
        restTemplate.delete(uri + "/" + account.getIBAN());
    }

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void createAccountResponse() throws Exception {
        HttpEntity<Account> request =
                new HttpEntity<>(account, headers);
        URI uri = new URI("http://localhost:" + port + "/account");
        ResponseEntity<String> response = restTemplate.
                postForEntity(uri, request, String.class);
        assertThat(response.getStatusCodeValue()).isEqualTo(HttpStatus.OK.value());
        assertThat(Objects.requireNonNull(response.getBody()).isEmpty()).isFalse();
    }

    @Test
    void createAccountBadResponse() throws Exception {
        HttpEntity<Account> request =
                new HttpEntity<>(new Account(), headers);
        URI uri = new URI("http://localhost:" + port + "/account");
        ResponseEntity<String> response = restTemplate.
                postForEntity(uri, request, String.class);
        assertThat(response.getStatusCodeValue()).isEqualTo(HttpStatus.BAD_REQUEST.value());
        assertThat(response.getBody()).isEqualTo("{\"amount\":\"Amount cannot be null\",\"IBAN\":\"IBAN cannot be null\",\"accountHolders\":\"Account holder must not be null\"}");

    }

    @Test
    void getAccount() throws Exception {
        HttpEntity<Account> request =
                new HttpEntity<>(account, headers);
        URI uri = new URI("http://localhost:" + port + "/account");
        ResponseEntity<String> response = restTemplate.
                postForEntity(uri, request, String.class);
        assertThat(response.getStatusCodeValue()).isEqualTo(HttpStatus.OK.value());

        Account response1 = restTemplate.
                getForObject(uri + "/" + account.getIBAN(), Account.class);
        assertThat(response1.getIBAN()).isEqualTo("1234");
    }

    @Test
    void getAccountNotFound() throws Exception {
        URI uri = new URI("http://localhost:" + port + "/account");
        ResponseEntity<Account> response1 = restTemplate.
                getForEntity(uri + "/" + "12345678900", Account.class);
        assertThat(response1.getStatusCodeValue()).isEqualTo(HttpStatus.NO_CONTENT.value());
    }

    @Test
    void updateAccount() throws Exception {
        HttpEntity<Account> request =
                new HttpEntity<>(account, headers);
        URI uri = new URI("http://localhost:" + port + "/account");

        ResponseEntity<String> response = restTemplate.
                postForEntity(uri, request, String.class);
        assertThat(response.getStatusCodeValue()).isEqualTo(HttpStatus.OK.value());

        Account response1 = restTemplate.
                getForObject(uri + "/" + account.getIBAN(), Account.class);
        assertThat(response1.getAmount()).isEqualTo(10000F);

        response1.setAmount(1000F);
        HttpEntity<Account> request1 =
                new HttpEntity<>(response1, headers);
        ResponseEntity<String> response2 = restTemplate.
                exchange(uri, HttpMethod.PUT, request1, String.class);
        assertThat(response2.getStatusCodeValue()).isEqualTo(HttpStatus.OK.value());

        Account response3 = restTemplate.
                getForObject(uri + "/" + account.getIBAN(), Account.class);
        assertThat(response3.getAmount()).isEqualTo(11000);

        response3.setAmount(-500F);
        HttpEntity<Account> request4 =
                new HttpEntity<>(response3, headers);
        ResponseEntity<String> response4 = restTemplate.
                exchange(uri, HttpMethod.PUT, request4, String.class);
        assertThat(response4.getStatusCodeValue()).isEqualTo(HttpStatus.OK.value());

        Account response5 = restTemplate.
                getForObject(uri + "/" + account.getIBAN(), Account.class);
        assertThat(response5.getAmount()).isEqualTo(10500F);
    }

    @Test
    void updateAccountHolders() throws Exception {
        HttpEntity<Account> request =
                new HttpEntity<>(account, headers);
        URI uri = new URI("http://localhost:" + port + "/account");

        ResponseEntity<String> response = restTemplate.
                postForEntity(uri, request, String.class);
        assertThat(response.getStatusCodeValue()).isEqualTo(HttpStatus.OK.value());

        Account response1 = restTemplate.
                getForObject(uri + "/" + account.getIBAN(), Account.class);
        assertThat(response1.getAccountHolders().size()).isEqualTo(2);

        response1.getAccountHolders().remove(0); // John
        HttpEntity<Account> request1 =
                new HttpEntity<>(response1, headers);
        ResponseEntity<String> response2 = restTemplate.
                exchange(uri, HttpMethod.PUT, request1, String.class);
        assertThat(response2.getStatusCodeValue()).isEqualTo(HttpStatus.OK.value());

        Account response3 = restTemplate.
                getForObject(uri + "/" + account.getIBAN(), Account.class);
        assertThat(response3.getAccountHolders().size()).isEqualTo(2);

        Account newAccount = new Account();
        newAccount.setIBAN(account.getIBAN());
        HashSet<AccountHolder> accountHolders = new HashSet<>();
        AccountHolder accountHolder = new AccountHolder();
        accountHolder.setFirstName("Kat");
        accountHolder.setLastName("Williams");
        accountHolders.add(accountHolder);
        newAccount.setAccountHolders(accountHolders);
        HttpEntity<Account> request4 =
                new HttpEntity<>(newAccount, headers);
        ResponseEntity<String> response4 = restTemplate.
                exchange(uri, HttpMethod.PUT, request4, String.class);
        assertThat(response4.getStatusCodeValue()).isEqualTo(HttpStatus.OK.value());

        Account response5 = restTemplate.
                getForObject(uri + "/" + account.getIBAN(), Account.class);
        assertThat(response5.getAccountHolders().size()).isEqualTo(1);
    }

    @Test
    void updateBlockAccount() throws Exception {
        HttpEntity<Account> request =
                new HttpEntity<>(account, headers);
        URI uri = new URI("http://localhost:" + port + "/account");
        ResponseEntity<String> response = restTemplate.
                postForEntity(uri, request, String.class);
        assertThat(response.getStatusCodeValue()).isEqualTo(HttpStatus.OK.value());

        Account response1 = restTemplate.
                getForObject(uri + "/" + account.getIBAN(), Account.class);
        assertThat(response1.isBlocked()).isFalse();

        response1.setBlocked(true);
        HttpEntity<Account> request1 =
                new HttpEntity<>(response1, headers);
        ResponseEntity<String> response2 = restTemplate.
                exchange(uri, HttpMethod.PUT, request1, String.class);
        assertThat(response2.getStatusCodeValue()).isEqualTo(HttpStatus.OK.value());

        Account response3 = restTemplate.
                getForObject(uri + "/" + account.getIBAN(), Account.class);
        assertThat(response3.isBlocked()).isTrue();

        Account newAccount = new Account();
        newAccount.setIBAN(account.getIBAN());
        newAccount.setBlocked(false);
        HttpEntity<Account> request4 =
                new HttpEntity<>(newAccount, headers);
        ResponseEntity<String> response4 = restTemplate.
                exchange(uri, HttpMethod.PUT, request4, String.class);
        assertThat(response4.getStatusCodeValue()).isEqualTo(HttpStatus.OK.value());

        Account response5 = restTemplate.
                getForObject(uri + "/" + account.getIBAN(), Account.class);
        assertThat(response5.isBlocked()).isFalse();
    }

    @Test
    void updateNonExistingBlockAccount() throws Exception {
        URI uri = new URI("http://localhost:" + port + "/account");
        Account account1 = new Account();
        account1.setIBAN("I don't exist");
        HttpEntity<Account> request =
                new HttpEntity<>(account1, headers);
        ResponseEntity<String> response = restTemplate.
                exchange(uri, HttpMethod.PUT, request, String.class);
        assertThat(response.getBody()).isEqualTo("\"NO_CONTENT\"");
    }

    @Test
    void deleteAccount() throws Exception {
        HttpEntity<Account> request =
                new HttpEntity<>(account, headers);
        URI uri = new URI("http://localhost:" + port + "/account");
        ResponseEntity<String> response = restTemplate.
                postForEntity(uri, request, String.class);
        assertThat(response.getStatusCodeValue()).isEqualTo(HttpStatus.OK.value());

        ResponseEntity<Account> response1 = restTemplate.
                getForEntity(uri + "/" + account.getIBAN(), Account.class);
        assertThat(response1.getStatusCodeValue()).isEqualTo(HttpStatus.OK.value());

        restTemplate.delete(uri + "/" + account.getIBAN());

        ResponseEntity<Account> response3 = restTemplate.
                getForEntity(uri + "/" + account.getIBAN(), Account.class);
        assertThat(response3.getStatusCode().value()).isEqualTo(HttpStatus.NO_CONTENT.value());
    }

    @Test
    void deleteNonExistingAccount() throws Exception {
        URI uri = new URI("http://localhost:" + port + "/account");
        ResponseEntity<Account> response = restTemplate.
                getForEntity(uri + "/" + "I don't exist", Account.class);
        assertThat(response.getStatusCodeValue()).isEqualTo(HttpStatus.NO_CONTENT.value());
    }
}