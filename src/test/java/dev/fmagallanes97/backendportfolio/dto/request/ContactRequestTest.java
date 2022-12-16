package dev.fmagallanes97.backendportfolio.dto.request;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@Tag("unit")
@DisplayName("Contact request validation test")
class ContactRequestTest {

    Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @ParameterizedTest(name = "given the email=''{1}'', then invalidate with the errorMessage=''{2}''")
    @CsvSource({
            "email, ron.weasley@.hogwarts, this value is not a valid email",
            "email, neville@edu, this value must be between 12 and 45 characters",
            "email,, this attribute is mandatory"
    })
    @DisplayName("It should invalidate email value with a constraint violation")
    void should_invalidate_wrong_email(String attribute, String value, String errorMessage) {
        // Given
        ContactRequest request = new ContactRequest(
                value,
                "https://github.com/hpotter",
                "https://linkedin.com/in/hpotter"
        );

        // When
        Set<ConstraintViolation<ContactRequest>> violations = validator.validate(request);

        // Then
        assertThat(violations).hasSize(1);

        ConstraintViolation<ContactRequest> violation = violations.iterator().next();

        assertThat(violation.getPropertyPath().toString()).hasToString(attribute);
        assertThat(violation.getMessage()).isEqualTo(errorMessage);
    }

    @Test
    @DisplayName("It should validate a contact request that passes all the constraint violations\n")
    void should_validate_request_successfully() {
        // Given
        ContactRequest request = new ContactRequest(
                "harry.potter@hogwarts.edu",
                "https://github.com/hpotter",
                "https://linkedin.com/in/hpotter"
        );

        // When
        Set<ConstraintViolation<ContactRequest>> constraintViolations = validator.validate(request);

        // Then
        assertThat(constraintViolations).isEmpty();
    }
}