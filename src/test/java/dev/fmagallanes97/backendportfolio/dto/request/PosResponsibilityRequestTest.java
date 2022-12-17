package dev.fmagallanes97.backendportfolio.dto.request;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
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

@Tag("unit")
@DisplayName("Position responsibility request validation test")
class PosResponsibilityRequestTest {

    Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @ParameterizedTest(name = "given the description=''{1}'', then invalidate the with errorMessage=''{2}''")
    @CsvSource({
            "description, Abc, this value must be between 4 and 255 characters",
            "description,, this attribute is mandatory"
    })
    @DisplayName("Given an invalid description value, it should produce a constraint violation")
    void should_invalidate_wrong_description_value(String attribute, String value, String errorMessage) {
        // Given
        PosResponsibilityRequest request = new PosResponsibilityRequest(value);

        // When
        Set<ConstraintViolation<PosResponsibilityRequest>> violations = validator.validate(request);

        // Then
        assertThat(violations).hasSize(1);

        ConstraintViolation<PosResponsibilityRequest> violation = violations.iterator().next();

        assertThat(violation.getPropertyPath().toString()).hasToString(attribute);
        assertThat(violation.getMessage()).isEqualTo(errorMessage);
    }

    @Test
    @DisplayName("Given a valid request, it should not produce any constraint violations")
    void should_validate_request() {
        // Given
        PosResponsibilityRequest request = new PosResponsibilityRequest("Design and implement new features for the company's product");

        // When
        Set<ConstraintViolation<PosResponsibilityRequest>> violations = validator.validate(request);

        // Then
        assertThat(violations).isEmpty();
    }

}
