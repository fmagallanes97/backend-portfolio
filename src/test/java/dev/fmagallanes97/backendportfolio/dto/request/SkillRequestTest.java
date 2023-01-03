package dev.fmagallanes97.backendportfolio.dto.request;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@Tag("unit")
@DisplayName("Skill request validation test")
class SkillRequestTest {

    Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @ParameterizedTest(name = "given the name=''{1}'', then invalidate with the errorMessage=''{2}''")
    @CsvSource(delimiter = '|', textBlock = """
                    name | Proficiency in implementing automated testing strategies using a variety of tools and frameworks (e.g. JUnit, Selenium, Cucumber) to ensure the quality and reliability of software systems, including unit, integration, and acceptance testing, as well as the ability to analyze and interpret test results. | this value must be between 4 and 255 characters
                    name | Fix | this value must be between 4 and 255 characters
                    name |     | this attribute is mandatory
            """)
    @DisplayName("Given an invalid name value, it should produce a constraint violation")
    void should_throw_constraint_violation_for_invalid_name_value(String attribute, String value, String errorMessage) {
        // Given
        SkillRequest request = new SkillRequest(value);

        // When
        Set<ConstraintViolation<SkillRequest>> violations = validator.validate(request);

        // Then
        assertThat(violations).hasSize(1);

        // And
        ConstraintViolation<SkillRequest> violation = violations.iterator().next();
        assertThat(violation.getPropertyPath().toString()).hasToString(attribute);
        assertThat(violation.getMessage()).isEqualTo(errorMessage);
    }

    @Test
    @DisplayName("Given a valid request, it should not produce any constraint violations")
    void should_validate_request_successfully() {
        // Given
        SkillRequest request = new SkillRequest("Proficiency in developing and deploying machine learning models using TensorFlow and PyTorch.");

        // When
        Set<ConstraintViolation<SkillRequest>> violations = validator.validate(request);

        // Then
        assertThat(violations).isEmpty();
    }
}
