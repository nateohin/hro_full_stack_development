package nl.caladus.hro.service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest
class StringServiceTest {


    private StringService stringUtilService = new StringService();


    @Test
    void reverseTest() {

        assertEquals(stringUtilService.reverse("test"), "tset");
        assertEquals(stringUtilService.reverse("1234"), "4321");
        assertNotEquals(stringUtilService.reverse("boddom"), "mddoob");
        assertNotEquals(stringUtilService.reverse("1234"), "1234");
        assertEquals(stringUtilService.reverse("  "), "  ");
        assertEquals(stringUtilService.reverse(""), "");


    }

}
