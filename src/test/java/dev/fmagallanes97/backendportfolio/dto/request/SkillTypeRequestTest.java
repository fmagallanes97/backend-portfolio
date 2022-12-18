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
@DisplayName("Skill type request validation test")
class SkillTypeRequestTest {

    Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @ParameterizedTest(name = "given the name=''{1}'', then invalidate with the errorMessage=''{2}''")
    @CsvSource(delimiter = '|', textBlock = """
                    name | In-depth knowledge of machine learning algorithms and their applications. | this value must be between 4 and 45 characters
                    name | Cat | this value must be between 4 and 45 characters
                    name |     | this attribute is mandatory
            """)
    @DisplayName("Given an invalid name value, it should produce a constraint violation")
    void should_throw_constraint_violation_for_invalid_name_value(String attribute, String value, String errorMessage) {
        // Given
        SkillTypeRequest request = new SkillTypeRequest(value);

        // When
        Set<ConstraintViolation<SkillTypeRequest>> violations = validator.validate(request);

        // Then
        assertThat(violations).hasSize(1);

        // And
        ConstraintViolation<SkillTypeRequest> violation = violations.iterator().next();
        assertThat(violation.getPropertyPath().toString()).hasToString(attribute);
        assertThat(violation.getMessage()).isEqualTo(errorMessage);
    }

    @Test
    @DisplayName("Given a valid request, it should not produce any constraint violations")
    void should_validate_request_successfully() {
        // Given
        SkillTypeRequest request = new SkillTypeRequest("Web development");

        // When
        Set<ConstraintViolation<SkillTypeRequest>> violations = validator.validate(request);

        // Then
        assertThat(violations).isEmpty();
    }
}
