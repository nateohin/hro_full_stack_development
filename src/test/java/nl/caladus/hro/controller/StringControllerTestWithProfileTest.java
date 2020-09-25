package nl.caladus.hro.controller;

import nl.caladus.hro.model.Input;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;

import java.net.URI;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("TEST")
class StringControllerTestWithProfileTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void correctReverseResponse() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Input> request =
                new HttpEntity<>(new Input("test"), headers);
        URI uri = new URI("http://localhost:" + port + "/words/reverse");
        ResponseEntity<String> response = restTemplate.
                postForEntity(uri, request, String.class);
        assertThat(response.getStatusCodeValue()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getBody()).isEqualTo("TEST");
    }

    @Test
    void emptytReverseResponse() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Input> request =
                new HttpEntity<>(new Input(), headers);
        URI uri = new URI("http://localhost:" + port + "/words/reverse");
        ResponseEntity<String> response = restTemplate.
                postForEntity(uri, request, String.class);
        assertThat(response.getStatusCodeValue()).isEqualTo(HttpStatus.BAD_REQUEST.value());
        assertThat(response.getBody()).isEqualTo("failed to process request");
    }
}