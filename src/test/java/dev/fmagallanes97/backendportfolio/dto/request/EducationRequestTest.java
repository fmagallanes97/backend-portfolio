package dev.fmagallanes97.backendportfolio.dto.request;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
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

@DisplayName("Education request validation test")
class EducationRequestTest {

    Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @ParameterizedTest(name = "when degree=''{1}'', then invalidate with errorMessage=''{2}''")
    @CsvSource({
            "degree, Boo, this value must be between 4 and 45 characters",
            "degree, Doctor of Transfiguration and Apparition with a focus on Defense Against the Dark Arts, this value must be between 4 and 45 characters",
            "degree,, this attribute is mandatory"
    })
    @DisplayName("It should invalidate degree value with a constraint violation")
    void should_invalidate_degree_value(String attribute, String value, String errorMessage) {
        EducationRequest request = new EducationRequest(
                value,
                "Hogwarts School of Witchcraft and Wizardry",
                "Transfiguration",
                LocalDate.of(1990, 9, 1),
                LocalDate.of(2017, 5, 31)
        );

        Set<ConstraintViolation<EducationRequest>> violations = validator.validate(request);

        assertThat(violations).hasSize(1);

        ConstraintViolation<EducationRequest> violation = violations.iterator().next();

        assertThat(violation.getPropertyPath().toString()).hasToString(attribute);
        assertThat(violation.getMessage()).isEqualTo(errorMessage);
    }

    @ParameterizedTest(name = "when school=''{1}'', then invalidate with errorMessage=''{2}''")
    @CsvSource({
            "school, Hog, this value must be between 4 and 45 characters",
            "school, Durmstrang Institute of Magic and Sorcery and Spells, this value must be between 4 and 45 characters",
            "school,, this attribute is mandatory"
    })
    @DisplayName("It should invalidate school value with a constraint violation")
    void should_invalidate_school_value(String attribute, String value, String errorMessage) {
        EducationRequest request = new EducationRequest(
                "Bachelor of Magic",
                value,
                "Transfiguration",
                LocalDate.of(1990, 9, 1),
                LocalDate.of(2017, 5, 31)
        );

        Set<ConstraintViolation<EducationRequest>> violations = validator.validate(request);

        assertThat(violations).hasSize(1);

        ConstraintViolation<EducationRequest> violation = violations.iterator().next();

        assertThat(violation.getPropertyPath().toString()).hasToString(attribute);
        assertThat(violation.getMessage()).isEqualTo(errorMessage);
    }

    @ParameterizedTest(name = "when academicField=''{1}'', then invalidate with errorMessage=''{2}''")
    @CsvSource({
            "academicField, Art, this value must be between 4 and 45 characters",
            "academicField, 'Magical Arts, Enchantment, and the Study of Mythical Creatures', this value must be between 4 and 45 characters",
            "academicField,, this attribute is mandatory"
    })
    @DisplayName("It should invalidate academic field value with a constraint violation")
    void should_invalidate_academic_field_value(String attribute, String value, String errorMessage) {
        EducationRequest request = new EducationRequest(
                "Bachelor of Magic",
                "Hogwarts School of Witchcraft and Wizardry",
                value,
                LocalDate.of(1990, 9, 1),
                LocalDate.of(2017, 5, 31)
        );

        Set<ConstraintViolation<EducationRequest>> violations = validator.validate(request);

        assertThat(violations).hasSize(1);

        ConstraintViolation<EducationRequest> violation = violations.iterator().next();

        assertThat(violation.getPropertyPath().toString()).hasToString(attribute);
        assertThat(violation.getMessage()).isEqualTo(errorMessage);
    }


    @Nested
    @DisplayName("It should invalidate start date value with a constraint violation")
    class startDate {

        @Test
        @DisplayName("when startDate='2050-1-1', then invalidate with errorMessage='this value must be between past and present time'")
        void should_invalidate_start_date_future_value() {
            EducationRequest request = new EducationRequest(
                    "Bachelor of Magic",
                    "Hogwarts School of Witchcraft and Wizardry",
                    "Transfiguration",
                    LocalDate.of(2050, 1, 1),
                    LocalDate.of(2017, 5, 31)
            );

            Set<ConstraintViolation<EducationRequest>> violations = validator.validate(request);

            assertThat(violations).hasSize(1);

            ConstraintViolation<EducationRequest> violation = violations.iterator().next();

            assertThat(violation.getPropertyPath().toString()).hasToString("startDate");
            assertThat(violation.getMessage()).isEqualTo("this value must be between past and present time");
        }

        @Test
        @DisplayName("when startDate='null', then invalidate with errorMessage='this attribute is mandatory'")
        void should_invalidate_start_date_null_value() {
            EducationRequest request = new EducationRequest(
                    "Bachelor of Magic",
                    "Hogwarts School of Witchcraft and Wizardry",
                    "Transfiguration",
                    null,
                    LocalDate.of(2017, 5, 31)
            );

            Set<ConstraintViolation<EducationRequest>> violations = validator.validate(request);

            assertThat(violations).hasSize(1);

            ConstraintViolation<EducationRequest> violation = violations.iterator().next();

            assertThat(violation.getPropertyPath().toString()).hasToString("startDate");
            assertThat(violation.getMessage()).isEqualTo("this attribute is mandatory");
        }

    }

    @ParameterizedTest(name = "when endDate=''{1}'', then invalidate with errorMessage=''{2}''")
    @CsvSource({
            "endDate, 2050-01-01, this value must be between past and present time"
    })
    @DisplayName("It should invalidate start date value with a constraint violation")
    void should_invalidate_end_date_value(String attribute, @JavaTimeConversionPattern("yyyy-MM-dd") LocalDate value, String errorMessage) {
        EducationRequest request = new EducationRequest(
                "Bachelor of Magic",
                "Hogwarts School of Witchcraft and Wizardry",
                "Transfiguration",
                LocalDate.of(2022, 9, 1),
                value
        );

        Set<ConstraintViolation<EducationRequest>> violations = validator.validate(request);

        assertThat(violations).hasSize(1);

        ConstraintViolation<EducationRequest> violation = violations.iterator().next();

        assertThat(violation.getPropertyPath().toString()).hasToString(attribute);
        assertThat(violation.getMessage()).isEqualTo(errorMessage);
    }

    @Test
    @DisplayName("It should validate degree value without constraint violations")
    void should_validate_education_request() {
        EducationRequest request = new EducationRequest(
                "Bachelor of Magic",
                "Hogwarts School of Witchcraft and Wizardry",
                "Transfiguration",
                LocalDate.of(1990, 9, 1),
                LocalDate.of(2017, 5, 31)
        );

        Set<ConstraintViolation<EducationRequest>> violations = validator.validate(request);

        assertThat(violations).hasSize(0);
    }
}