package nl.caladus.hro.controller;

import nl.caladus.hro.model.Input;
import nl.caladus.hro.service.StringService;
import nl.caladus.hro.utils.PredicateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/words")
public class StringController {

    private final Environment environment;
    private final StringService stringService;

    public StringController(Environment environment, StringService stringService) {
        this.environment = environment;
        this.stringService = stringService;
    }

    @PostMapping("/reverse")
    public ResponseEntity stringReverse(@RequestBody Input input) {

        try {
            List<String> profiles = Arrays.asList(environment.getActiveProfiles());
            if (PredicateUtils.equals(profiles, "TEST")) {
                return ResponseEntity.ok(input.getTest().toUpperCase());
            }

            return ResponseEntity.ok(stringService.reverse(input.getTest()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("failed to process request");
        }

    }

    @PostMapping("/count")
    public ResponseEntity textLength(@RequestBody Input input) {

        try {
            return ResponseEntity.ok(stringService.count(input.getTest()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("failed to process request");
        }
    }

}
