package nl.caladus.hro.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class AccountTest {

    private Account account;

    @BeforeEach
    void setUp() {
        account = new Account("1234567890", 1000F, null);
    }

    @Test
    void readAccountValidation() {

        // Given
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        // Given
        account.setAmount(0);
        Optional<ConstraintViolation<Account>> violation = validator.validate(account).stream().findFirst();

        // Then
        assertThat(violation.isPresent()).isTrue();
        assertThat(violation.get().getMessage()).isEqualTo("AccountHolders cannot be null");

    }

}