package nl.caladus.hro.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class StringServiceTest {

    @Autowired
    private StringService stringUtilService;


    @Test
    void correctReverseTest() {

        assertThat(stringUtilService.reverse("test")).isEqualTo( "tset");
        assertThat(stringUtilService.reverse("1234")).isEqualTo( "4321");
        assertThat(stringUtilService.reverse("  ")).isEqualTo( "  ");
        assertThat(stringUtilService.reverse(("")).isEmpty());

    }

    @Test
    void incorrectReverseTest() {
        assertThat(stringUtilService.reverse("boddom")).isNotEqualTo( "mddoob");
        assertThat(stringUtilService.reverse("1234")).isNotEqualTo( "1234");
    }

    @Test
    void emptyReverseTest() {
        assertThrows(RuntimeException.class, () ->
                stringUtilService.reverse(null));
    }

    @Test
    void correctCountTest() {

        assertThat(stringUtilService.count("test me please")).isEqualTo( 3);
        assertThat(stringUtilService.count("Try again!")).isEqualTo( 2);

    }

    @Test
    void emptyCountTest() {
        assertThrows(RuntimeException.class, () ->
                stringUtilService.count(null));
    }

}
