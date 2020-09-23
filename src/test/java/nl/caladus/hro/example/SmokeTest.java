package nl.caladus.hro.example;

import nl.caladus.hro.controller.StringController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
public class SmokeTest {

    @Autowired
    private StringController stringController;

    @Test
    public void contextLoads() throws Exception {
        assertThat(stringController).isNotNull();
    }
}