package nl.caladus.hro.controller;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.BDDAssertions.then;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = {"management.port=0"})
class HomeControllerTest {

    @LocalServerPort
    private int port;

    @Value("${local.management.port}")
    private int mgt;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void greetingShouldReturnDefaultMessage() throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/",
                String.class)).contains("Hello, World");
    }

    @Test
    void shouldReturn200WhenSendingRequestToManagementEndpoint() throws Exception {
        @SuppressWarnings("rawtypes")
        ResponseEntity<Map> entity = this.restTemplate.getForEntity(
                "http://localhost:" + this.mgt + "/actuator/info", Map.class);

        then(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

}