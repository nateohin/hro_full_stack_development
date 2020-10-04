package nl.caladus.hro.example;

import nl.caladus.hro.controller.AccountController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
class SmokeTest {

    @Autowired
    private AccountController accountController;

    @Test
    void contextLoads() {
        assertThat(accountController).isNotNull();
    }
}