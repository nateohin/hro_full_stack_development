package nl.caladus.hro.controller;

import nl.caladus.hro.model.Account;
import nl.caladus.hro.model.AccountHolder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;

import java.net.URI;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class AccountControllerTest {

    @LocalServerPort
    private int port;

    private HttpHeaders headers = new HttpHeaders();
    private Account account = new Account();

    @BeforeEach
    void setUp() {
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        AccountHolder accountHolder1 = new AccountHolder("John", "Doe");
        AccountHolder accountHolder2 = new AccountHolder("Jane", "Doe");
        account = new Account("1234567890", 1000.00F, Arrays.asList(accountHolder1, accountHolder2));
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
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Account> request =
                new HttpEntity<>(new Account(), headers);
        URI uri = new URI("http://localhost:" + port + "/account");
        ResponseEntity<String> response = restTemplate.
                postForEntity(uri, request, String.class);
        assertThat(response.getStatusCodeValue()).isEqualTo(HttpStatus.BAD_REQUEST.value());
        assertThat(response.getBody()).isEqualTo("{\"IBAN\":\"IBAN cannot be null\",\"accountHolders\":\"AccountHolders cannot be null\"}");

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
        assertThat(response1.getIBAN()).isEqualTo("1234567890");
    }

    @Test
    void getAccounts() throws Exception {
        HttpEntity<Account> request =
                new HttpEntity<>(account, headers);
        URI uri = new URI("http://localhost:" + port + "/account");
        ResponseEntity<String> response = restTemplate.
                postForEntity(uri, request, String.class);
        assertThat(response.getStatusCodeValue()).isEqualTo(HttpStatus.OK.value());

        Account response1 = restTemplate.
                getForObject(uri + "/" + account.getIBAN(), Account.class);
        assertThat(response1.getIBAN()).isEqualTo("1234567890");

        List accounts1 = restTemplate.
                getForObject(uri,  List.class);
        assertThat(accounts1.size()).isEqualTo(1);

        for (int i = 0; i <= 10; i++) {
            AccountHolder accountHolder1 = new AccountHolder("John" + i, "Doe" + i);
            AccountHolder accountHolder2 = new AccountHolder("Jane" + i, "Doe" + i);
            Account account1 = new Account("123456789" + i, 1000.00F, Arrays.asList(accountHolder1, accountHolder2));

            HttpEntity<Account> request1 =
                    new HttpEntity<>(account1, headers);
            ResponseEntity<String> response2 = restTemplate.
                    postForEntity(uri, request1, String.class);
            assertThat(response2.getStatusCodeValue()).isEqualTo(HttpStatus.OK.value());
        }
        List accounts2 = restTemplate.
                getForObject(uri,  List.class);
        assertThat(accounts2.size()).isEqualTo(11);
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
        assertThat(response1.getAmount()).isEqualTo(1000);

        response1.setAmount(1000);
        HttpEntity<Account> request1 =
                new HttpEntity<>(response1, headers);
        ResponseEntity<String> response2 = restTemplate.
                exchange(uri, HttpMethod.PUT, request1, String.class);
        assertThat(response2.getStatusCodeValue()).isEqualTo(HttpStatus.OK.value());

        Account response3 = restTemplate.
                getForObject(uri + "/" + account.getIBAN(), Account.class);
        assertThat(response3.getAmount()).isEqualTo(2000);

        response3.setAmount(-500);
        HttpEntity<Account> request4 =
                new HttpEntity<>(response3, headers);
        ResponseEntity<String> response4 = restTemplate.
                exchange(uri, HttpMethod.PUT, request4, String.class);
        assertThat(response4.getStatusCodeValue()).isEqualTo(HttpStatus.OK.value());

        Account response5 = restTemplate.
                getForObject(uri + "/" + account.getIBAN(), Account.class);
        assertThat(response5.getAmount()).isEqualTo(1500);
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
        assertThat(response1.getAccountHolders().toString()).isEqualTo(account.getAccountHolders().toString());

        response1.getAccountHolders().remove(0); // John
        HttpEntity<Account> request1 =
                new HttpEntity<>(response1, headers);
        ResponseEntity<String> response2 = restTemplate.
                exchange(uri, HttpMethod.PUT, request1, String.class);
        assertThat(response2.getStatusCodeValue()).isEqualTo(HttpStatus.OK.value());

        Account response3 = restTemplate.
                getForObject(uri + "/" + account.getIBAN(), Account.class);
        assertThat(response3.getAccountHolders().size()).isEqualTo(1);

        Account newAccount = new Account();
        newAccount.setIBAN(account.getIBAN());
        newAccount.setAccountHolders(Collections.singletonList(new AccountHolder("Kat", "Williams")));
        HttpEntity<Account> request4 =
                new HttpEntity<>(newAccount, headers);
        ResponseEntity<String> response4 = restTemplate.
                exchange(uri, HttpMethod.PUT, request4, String.class);
        assertThat(response4.getStatusCodeValue()).isEqualTo(HttpStatus.OK.value());

        Account response5 = restTemplate.
                getForObject(uri + "/" + account.getIBAN(), Account.class);
        assertThat(response5.getAccountHolders().size()).isEqualTo(2);
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

        Account account1 = new Account();
        account1.setBlocked(true);
        account1.setIBAN(response1.getIBAN());
        HttpEntity<Account> request1 =
                new HttpEntity<>(account1, headers);
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
        assertThat(response.getStatusCodeValue()).isEqualTo(HttpStatus.NO_CONTENT.value());;
    }
}