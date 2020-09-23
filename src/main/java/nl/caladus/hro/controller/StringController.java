package nl.caladus.hro.controller;

import nl.caladus.hro.service.StringService;
import nl.caladus.hro.utils.PredicateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/words")
public class StringController {

    @Autowired
    private Environment environment;

    @Autowired
    private StringService stringService;

    @PostMapping("/reverse")
    public String stringReverse(String input) {

        List<String> profiles = Arrays.asList(environment.getActiveProfiles());
        if (PredicateUtils.equals(profiles, "TEST")) {
            return input.toUpperCase();
        }

        return stringService.reverse(input);
    }

    @PostMapping("/length")
    public int textLength(String input) {
        return stringService.length(input);
    }

}
