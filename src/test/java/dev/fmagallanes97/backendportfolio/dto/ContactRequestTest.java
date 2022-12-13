package dev.fmagallanes97.backendportfolio.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Contact request")
class ContactRequestTest {

    Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @ParameterizedTest(name = "when email=''{0}'', then notify with errorMessage=''{1}''")
    @CsvSource({
            "michae_cappsz6j2buy.xvr, this value is not a valid email",
            "m@buy.xvr, this value must be between 12 and 45 characters",
            ", this attribute is mandatory"
    })
    @DisplayName("It should notify when creating contact with an invalid email value")
    void should_fail_creating_contact_with_invalid_email_value(String email, String errorMessage) {
        ContactRequest contactRequest = new ContactRequest(email, "", "");
        Set<ConstraintViolation<ContactRequest>> constraintViolations = validator.validate(contactRequest);

        assertThat(constraintViolations).hasSize(1);

        ConstraintViolation<ContactRequest> constraintViolation = constraintViolations.iterator().next();

        assertThat(constraintViolation.getPropertyPath().toString()).hasToString("email");
        assertThat(constraintViolation.getMessage()).isEqualTo(errorMessage);
    }

    @Test
    @DisplayName("It should pass when creating contact respecting all email constraints violations")
    void should_pass_contact_constraints() {
        ContactRequest contactRequest = new ContactRequest("shadawn_eichlerj@anthony.nd", "", "");
        Set<ConstraintViolation<ContactRequest>> constraintViolations = validator.validate(contactRequest);

        assertThat(constraintViolations).isEmpty();
    }
}