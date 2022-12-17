package dev.fmagallanes97.backendportfolio.dto.request;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.JavaTimeConversionPattern;
import org.junit.jupiter.params.provider.CsvSource;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.time.LocalDate;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@Tag("unit")
@DisplayName("Education request validation test")
class EducationRequestTest {

    Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @ParameterizedTest(name = "given the degree=''{1}'', then invalidate with the errorMessage=''{2}''")
    @CsvSource({
            "degree, Boo, this value must be between 4 and 45 characters",
            "degree, Doctor of Transfiguration and Apparition with a focus on Defense Against the Dark Arts, this value must be between 4 and 45 characters",
            "degree,, this attribute is mandatory"
    })
    @DisplayName("Given an invalid degree value, it should produce a constraint violation")
    void should_invalidate_wrong_degree_value(String attribute, String value, String errorMessage) {
        // Given
        EducationRequest request = new EducationRequest(
                value,
                "Hogwarts School of Witchcraft and Wizardry",
                "Transfiguration",
                LocalDate.of(1990, 9, 1),
                LocalDate.of(2017, 5, 31)
        );

        // When
        Set<ConstraintViolation<EducationRequest>> violations = validator.validate(request);

        // Then
        assertThat(violations).hasSize(1);

        ConstraintViolation<EducationRequest> violation = violations.iterator().next();

        assertThat(violation.getPropertyPath().toString()).hasToString(attribute);
        assertThat(violation.getMessage()).isEqualTo(errorMessage);
    }

    @ParameterizedTest(name = "given the school=''{1}'', then invalidate with the errorMessage=''{2}''")
    @CsvSource({
            "school, Hog, this value must be between 4 and 45 characters",
            "school, Durmstrang Institute of Magic and Sorcery and Spells, this value must be between 4 and 45 characters",
            "school,, this attribute is mandatory"
    })
    @DisplayName("Given an invalid school value, it should produce a constraint violation")
    void should_invalidate_wrong_school_value(String attribute, String value, String errorMessage) {
        // Given
        EducationRequest request = new EducationRequest(
                "Bachelor of Magic",
                value,
                "Transfiguration",
                LocalDate.of(1990, 9, 1),
                LocalDate.of(2017, 5, 31)
        );

        // When
        Set<ConstraintViolation<EducationRequest>> violations = validator.validate(request);

        // Then
        assertThat(violations).hasSize(1);

        ConstraintViolation<EducationRequest> violation = violations.iterator().next();

        assertThat(violation.getPropertyPath().toString()).hasToString(attribute);
        assertThat(violation.getMessage()).isEqualTo(errorMessage);
    }

    @ParameterizedTest(name = "given the academicField=''{1}'', then invalidate with the errorMessage=''{2}''")
    @CsvSource({
            "academicField, Art, this value must be between 4 and 45 characters",
            "academicField, 'Magical Arts, Enchantment, and the Study of Mythical Creatures', this value must be between 4 and 45 characters",
            "academicField,, this attribute is mandatory"
    })
    @DisplayName("Given an invalid academic field value, it should produce a constraint violation")
    void should_invalidate_wrong_academic_field_value(String attribute, String value, String errorMessage) {
        // Given
        EducationRequest request = new EducationRequest(
                "Bachelor of Magic",
                "Hogwarts School of Witchcraft and Wizardry",
                value,
                LocalDate.of(1990, 9, 1),
                LocalDate.of(2017, 5, 31)
        );

        // When
        Set<ConstraintViolation<EducationRequest>> violations = validator.validate(request);

        // Then
        assertThat(violations).hasSize(1);

        ConstraintViolation<EducationRequest> violation = violations.iterator().next();

        assertThat(violation.getPropertyPath().toString()).hasToString(attribute);
        assertThat(violation.getMessage()).isEqualTo(errorMessage);
    }

    @Nested
    @DisplayName("Given an invalid start date value, it should produce a constraint violation")
    class should_invalidate_wrong_start_date {

        @Test
        @DisplayName("given the startDate='2050-1-1', then invalidate with the errorMessage='this value must be between past and present time'")
        void should_invalidate_start_date_future_value() {
            // Given
            EducationRequest request = new EducationRequest(
                    "Bachelor of Magic",
                    "Hogwarts School of Witchcraft and Wizardry",
                    "Transfiguration",
                    LocalDate.of(2050, 1, 1),
                    LocalDate.of(2017, 5, 31)
            );

            // When
            Set<ConstraintViolation<EducationRequest>> violations = validator.validate(request);

            // Then
            assertThat(violations).hasSize(1);

            ConstraintViolation<EducationRequest> violation = violations.iterator().next();

            assertThat(violation.getPropertyPath().toString()).hasToString("startDate");
            assertThat(violation.getMessage()).isEqualTo("this value must be between past and present time");
        }

        @Test
        @DisplayName("given the startDate='null', then invalidate with the errorMessage='this attribute is mandatory'")
        void should_invalidate_start_date_null_value() {
            // Given
            EducationRequest request = new EducationRequest(
                    "Bachelor of Magic",
                    "Hogwarts School of Witchcraft and Wizardry",
                    "Transfiguration",
                    null,
                    LocalDate.of(2017, 5, 31)
            );

            // When
            Set<ConstraintViolation<EducationRequest>> violations = validator.validate(request);

            // Then
            assertThat(violations).hasSize(1);

            ConstraintViolation<EducationRequest> violation = violations.iterator().next();

            assertThat(violation.getPropertyPath().toString()).hasToString("startDate");
            assertThat(violation.getMessage()).isEqualTo("this attribute is mandatory");
        }

    }

    @ParameterizedTest(name = "given the endDate=''{1}'', then invalidate with the errorMessage=''{2}''")
    @CsvSource({
            "endDate, 2050-01-01, this value must be between past and present time"
    })
    @DisplayName("Given an invalid end date value, it should produce a constraint violation")
    void should_invalidate_wrong_end_date_value(String attribute, @JavaTimeConversionPattern("yyyy-MM-dd") LocalDate value, String errorMessage) {
        // Given
        EducationRequest request = new EducationRequest(
                "Bachelor of Magic",
                "Hogwarts School of Witchcraft and Wizardry",
                "Transfiguration",
                LocalDate.of(2022, 9, 1),
                value
        );

        // When
        Set<ConstraintViolation<EducationRequest>> violations = validator.validate(request);

        // Then
        assertThat(violations).hasSize(1);

        ConstraintViolation<EducationRequest> violation = violations.iterator().next();

        assertThat(violation.getPropertyPath().toString()).hasToString(attribute);
        assertThat(violation.getMessage()).isEqualTo(errorMessage);
    }

    @Test
    @DisplayName("Given a valid request, it should not produce any constraint violations")
    void should_validate_request_successfully() {
        // Given
        EducationRequest request = new EducationRequest(
                "Bachelor of Magic",
                "Hogwarts School of Witchcraft and Wizardry",
                "Transfiguration",
                LocalDate.of(1990, 9, 1),
                LocalDate.of(2017, 5, 31)
        );

        // When
        Set<ConstraintViolation<EducationRequest>> violations = validator.validate(request);

        // Then
        assertThat(violations).isEmpty();
    }
}