package nl.caladus.hro.utils;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class PredicateUtilsTest {

    @Test
    void equalsTest() {

        assertThat(PredicateUtils.equals(Arrays.asList(new String[]{"test", "me"}), "test")).isTrue();
        assertThat(PredicateUtils.equals(Arrays.asList(new String[]{"test", "me"}), "not")).isFalse();
    }
}
