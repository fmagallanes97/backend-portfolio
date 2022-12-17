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
@DisplayName("Position request validation test")
class PositionRequestTest {

    Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @ParameterizedTest(name = "given the role=''{1}'', then invalidate with the errorMessage=''{2}''")
    @CsvSource({
            "role, Eng, this value must be between 4 and 45 characters",
            "role,, this attribute is mandatory"
    })
    @DisplayName("Given an invalid role value, it should produce a constraint violation")
    void should_invalidate_wrong_role_value(String attribute, String value, String errorMessage) {
        // Given
        PositionRequest request = new PositionRequest(
                value,
                "Acme Corp",
                LocalDate.of(2020, 1, 1),
                LocalDate.of(2021, 1, 1)
        );

        // When
        Set<ConstraintViolation<PositionRequest>> violations = validator.validate(request);

        // Then
        assertThat(violations).hasSize(1);

        ConstraintViolation<PositionRequest> violation = violations.iterator().next();

        assertThat(violation.getPropertyPath().toString()).hasToString(attribute);
        assertThat(violation.getMessage()).isEqualTo(errorMessage);
    }

    @ParameterizedTest(name = "given the companyName=''{1}'', then invalidate the with errorMessage=''{2}''")
    @CsvSource({
            "companyName, Inc, this value must be between 4 and 45 characters",
            "companyName,, this attribute is mandatory"
    })
    @DisplayName("Given an invalid company name value, it should produce a constraint violation")
    void should_invalidate_wrong_company_name_value(String attribute, String value, String errorMessage) {
        // Given
        PositionRequest request = new PositionRequest(
                "Software Engineer",
                value,
                LocalDate.of(2020, 1, 1),
                LocalDate.of(2021, 1, 1)
                );

        // When
        Set<ConstraintViolation<PositionRequest>> violations = validator.validate(request);

        // Then
        assertThat(violations).hasSize(1);

        ConstraintViolation<PositionRequest> violation = violations.iterator().next();

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
            PositionRequest request = new PositionRequest(
                    "Software Engineer",
                    "Acme Corp",
                    LocalDate.of(2050, 1, 1),
                    LocalDate.of(2021, 1, 1)
            );

            // When
            Set<ConstraintViolation<PositionRequest>> violations = validator.validate(request);

            // Then
            assertThat(violations).hasSize(1);

            ConstraintViolation<PositionRequest> violation = violations.iterator().next();

            assertThat(violation.getPropertyPath().toString()).hasToString("startDate");
            assertThat(violation.getMessage()).isEqualTo("this value must be between past and present time");
        }

        @Test
        @DisplayName("given the startDate='null', then invalidate with the errorMessage='this attribute is mandatory'")
        void should_invalidate_start_date_null_value() {
            // Given
            PositionRequest request = new PositionRequest(
                    "Software Engineer",
                    "Acme Corp",
                    null,
                    LocalDate.of(2021, 1, 1)
            );

            // When
            Set<ConstraintViolation<PositionRequest>> violations = validator.validate(request);

            // Then
            assertThat(violations).hasSize(1);

            ConstraintViolation<PositionRequest> violation = violations.iterator().next();

            assertThat(violation.getPropertyPath().toString()).hasToString("startDate");
            assertThat(violation.getMessage()).isEqualTo("this attribute is mandatory");
        }
    }

    @ParameterizedTest(name = "given the endDate=''{1}'', then invalidate with the errorMessage=''{2}''")
    @CsvSource({
            "endDate, 2050-01-01, this value must be between past and present time"
    })
    @DisplayName("Given an invalid end date value, it should produce a constraint violation")
    void should_invalidate_wrong_end_date(String attribute, @JavaTimeConversionPattern("yyyy-MM-dd") LocalDate value, String errorMessage) {
        // Given
        PositionRequest request = new PositionRequest(
                "Software Engineer",
                "Acme Corp",
                LocalDate.of(2020, 1, 1),
                LocalDate.of(2050, 1, 1)
        );

        // When
        Set<ConstraintViolation<PositionRequest>> violations = validator.validate(request);

        // Then
        assertThat(violations).hasSize(1);

        ConstraintViolation<PositionRequest> violation = violations.iterator().next();

        assertThat(violation.getPropertyPath().toString()).hasToString(attribute);
        assertThat(violation.getMessage()).isEqualTo(errorMessage);
    }

    @Test
    @DisplayName("Given a valid request, it should not produce any constraint violations")
    void should_validate_request_successfully() {
        // Given
        PositionRequest request = new PositionRequest(
                "Software Engineer",
                "Acme Corp",
                LocalDate.of(2020, 1, 1),
                LocalDate.of(2021, 1, 1)
        );

        // When
        Set<ConstraintViolation<PositionRequest>> violations = validator.validate(request);

        // Then
        assertThat(violations).isEmpty();
    }
}
