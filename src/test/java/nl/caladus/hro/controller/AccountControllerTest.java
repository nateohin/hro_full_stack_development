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
import java.util.Arrays;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class AccountControllerTest {

    @LocalServerPort
    private int port;

    private HttpHeaders headers;
    private Account account;

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
        assertThat(response.getBody().isEmpty()).isFalse();
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

        ResponseEntity<Account> response1 = restTemplate.
                getForEntity(uri + "/" + account.getIBAN(), Account.class);
        assertThat(response1.getStatusCodeValue()).isEqualTo(HttpStatus.OK.value());

        account.setAmount(1000);
        HttpEntity<Account> request1 =
                new HttpEntity<>(account, headers);
        ResponseEntity<String> response2 = restTemplate.
                exchange(uri, HttpMethod.PUT, request1, String.class);
        assertThat(response2.getStatusCodeValue()).isEqualTo(HttpStatus.OK.value());

        Account response3 = restTemplate.
                getForObject(uri + "/" + account.getIBAN(), Account.class);
        // TODO
        assertThat(response3.getAmount()).isEqualTo(3000);

        account.setAmount(-500);
        HttpEntity<Account> request4 =
                new HttpEntity<>(account, headers);
        ResponseEntity<String> response4 = restTemplate.
                exchange(uri, HttpMethod.PUT, request4, String.class);
        assertThat(response4.getStatusCodeValue()).isEqualTo(HttpStatus.OK.value());

        Account response5 = restTemplate.
                getForObject(uri + "/" + account.getIBAN(), Account.class);
        // TODO
        assertThat(response5.getAmount()).isEqualTo(4500);
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

        ResponseEntity<Map> response3 = restTemplate.
                getForEntity(uri + "/" + account.getIBAN(), Map.class);
        assertThat(response3.getStatusCode().value()).isEqualTo(204);
    }


}