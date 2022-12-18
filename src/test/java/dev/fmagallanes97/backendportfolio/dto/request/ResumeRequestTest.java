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
@DisplayName("Resume request validation test")
class ResumeRequestTest {

    Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @ParameterizedTest(name = "given the firstName=''{1}'', then invalidate with the errorMessage=''{2}''")
    @CsvSource(delimiter = '|', textBlock = """
                    firstName | Cedric Diggory-Weasley-Potter-Granger-Longbottom | this value must be between 2 and 45 characters
                    firstName | H | this value must be between 2 and 45 characters
                    firstName |   | this attribute is mandatory
            """)
    @DisplayName("Given an invalid first name value, it should produce a constraint violation")
    void should_throw_constraint_violation_for_invalid_first_name_value(String attribute, String value, String errorMessage) {
        // Given
        ResumeRequest request = new ResumeRequest(
                value,
                "Longbottom",
                "Professional Wizard",
                "I am a professional wizard with experience in defeating dark forces and protecting the wizarding world."
        );

        // When
        Set<ConstraintViolation<ResumeRequest>> violations = validator.validate(request);

        // Then
        assertThat(violations).hasSize(1);

        // And
        ConstraintViolation<ResumeRequest> violation = violations.iterator().next();
        assertThat(violation.getPropertyPath().toString()).hasToString(attribute);
        assertThat(violation.getMessage()).isEqualTo(errorMessage);
    }

    @ParameterizedTest(name = "given the lastName=''{1}'', then invalidate with the errorMessage=''{2}''")
    @CsvSource(delimiter = '|', textBlock = """
                    lastName | Hufflepuff-Black-Potter-Weasley-Granger-Longbottom | this value must be between 2 and 45 characters
                    lastName | W | this value must be between 2 and 45 characters
                    lastName |   | this attribute is mandatory
            """)
    @DisplayName("Given an invalid last name value, it should produce a constraint violation")
    void should_throw_constraint_violation_for_invalid_last_name_value(String attribute, String value, String errorMessage) {
        // Given
        ResumeRequest request = new ResumeRequest(
                "Neville",
                value,
                "Professional Wizard",
                "I am a professional wizard with experience in defeating dark forces and protecting the wizarding world."
        );

        // When
        Set<ConstraintViolation<ResumeRequest>> violations = validator.validate(request);

        // Then
        assertThat(violations).hasSize(1);

        // And
        ConstraintViolation<ResumeRequest> violation = violations.iterator().next();
        assertThat(violation.getPropertyPath().toString()).hasToString(attribute);
        assertThat(violation.getMessage()).isEqualTo(errorMessage);
    }

    @ParameterizedTest(name = "given the headline=''{1}'', then invalidate with the errorMessage=''{2}''")
    @CsvSource(delimiter = '|', textBlock = """
                    headline | Experienced potions master seeking challenging role in magic industry. | this value must be between 4 and 45 characters
                    headline | Pro | this value must be between 4 and 45 characters
                    headline |     | this attribute is mandatory
            """)
    @DisplayName("Given an invalid headline value, it should produce a constraint violation")
    void should_throw_constraint_violation_for_invalid_headline_value(String attribute, String value, String errorMessage) {
        // Given
        ResumeRequest request = new ResumeRequest(
                "Neville",
                "Longbottom",
                value,
                "I am a professional wizard with experience in defeating dark forces and protecting the wizarding world."
        );

        // When
        Set<ConstraintViolation<ResumeRequest>> violations = validator.validate(request);

        // Then
        assertThat(violations).hasSize(1);

        // And
        ConstraintViolation<ResumeRequest> violation = violations.iterator().next();
        assertThat(violation.getPropertyPath().toString()).hasToString(attribute);
        assertThat(violation.getMessage()).isEqualTo(errorMessage);
    }

    @ParameterizedTest(name = "given the about=''{1}'', then invalidate with the errorMessage=''{2}''")
    @CsvSource(delimiter = '|', textBlock = """
                    about | A skilled witch with a passion for potions and herbology. Proven track record of success in potion-making and successful removal of curses. Proficient in the use of magic for defense and healing. Looking for a challenging role in the wizarding world where I can utilize my skills and continue to learn and grow. | this value must be between 20 and 255 characters
                    about | I am | this value must be between 20 and 255 characters
                    about |      | this attribute is mandatory
            """)
    @DisplayName("Given an invalid about section value, it should produce a constraint violation")
    void should_throw_constraint_violation_for_invalid_about_value(String attribute, String value, String errorMessage) {
        // Given
        ResumeRequest request = new ResumeRequest(
                "Neville",
                "Longbottom",
                "Professional Wizard",
                value
        );

        // When
        Set<ConstraintViolation<ResumeRequest>> violations = validator.validate(request);

        // Then
        assertThat(violations).hasSize(1);

        // And
        ConstraintViolation<ResumeRequest> violation = violations.iterator().next();
        assertThat(violation.getPropertyPath().toString()).hasToString(attribute);
        assertThat(violation.getMessage()).isEqualTo(errorMessage);
    }

    @Test
    @DisplayName("Given a valid request, it should not produce any constraint violations")
    void should_validate_request_successfully() {
        // Given
        ResumeRequest request = new ResumeRequest(
                "Neville",
                "Longbottom",
                "Professional Wizard",
                "I am a professional wizard with experience in defeating dark forces and protecting the wizarding world."
        );

        // When
        Set<ConstraintViolation<ResumeRequest>> violations = validator.validate(request);

        // Then
        assertThat(violations).isEmpty();
    }
}