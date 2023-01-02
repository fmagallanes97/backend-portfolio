package dev.fmagallanes97.backendportfolio.dto.request;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.JavaTimeConversionPattern;
import org.junit.jupiter.params.provider.CsvSource;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import java.time.LocalDate;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@Tag("unit")
@DisplayName("Project request validation test")
class ProjectRequestTest {

    Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @ParameterizedTest(name = "given the title=''{1}'', then invalidate with the errorMessage=''{2}''")
    @CsvSource(delimiter = '|', textBlock = """
                    title | The NextGen project: Revolutionizing industry with advanced AI and robotics. | this value must be between 4 and 45 characters
                    title | The | this value must be between 4 and 45 characters
                    title |     | this attribute is mandatory
            """)
    @DisplayName("Given an invalid title value, it should produce a constraint violation")
    void should_throw_constraint_violation_for_invalid_title_value(String attribute, String value, String errorMessage) {
        // Given
        ProjectRequest request = new ProjectRequest(
                value,
                "The NextGen project is a revolutionary new system that combines advanced artificial intelligence with cutting-edge robotics to create a fully automated solution for a variety of industries.",
                LocalDate.of(2020, 1, 1),
                "https://nextgenproject.com",
                "https://github.com/nextgenproject/repository",
                "https://nextgenproject.com/static/preview.jpg"
        );

        // When
        Set<ConstraintViolation<ProjectRequest>> violations = validator.validate(request);

        // Then
        assertThat(violations).hasSize(1);

        // And
        ConstraintViolation<ProjectRequest> violation = violations.iterator().next();
        assertThat(violation.getPropertyPath().toString()).hasToString(attribute);
        assertThat(violation.getMessage()).isEqualTo(errorMessage);
    }

    @ParameterizedTest(name = "given the description=''{1}'', then invalidate with the errorMessage=''{2}''")
    @CsvSource(delimiter = '|', textBlock = """
                    description | The NextGen project is a groundbreaking new system that combines advanced artificial intelligence with cutting-edge robotics to create a fully automated solution for a wide range of industries. Our innovative technology has the potential to revolutionize the way businesses operate, improving efficiency and productivity while reducing costs and increasing profits. | this value must be between 12 and 255 characters
                    description | NextGen | this value must be between 12 and 255 characters
                    description |         | this attribute is mandatory
            """)
    @DisplayName("Given an invalid description value, it should produce a constraint violation")
    void should_throw_constraint_violation_for_invalid_description_value(String attribute, String value, String errorMessage) {
        // Given
        ProjectRequest request = new ProjectRequest(
                "NextGen",
                value,
                LocalDate.of(2020, 1, 1),
                "https://nextgenproject.com",
                "https://github.com/nextgenproject/repository",
                "https://nextgenproject.com/static/preview.jpg"
        );

        // When
        Set<ConstraintViolation<ProjectRequest>> violations = validator.validate(request);

        // Then
        assertThat(violations).hasSize(1);

        // And
        ConstraintViolation<ProjectRequest> violation = violations.iterator().next();
        assertThat(violation.getPropertyPath().toString()).hasToString(attribute);
        assertThat(violation.getMessage()).isEqualTo(errorMessage);
    }

    @ParameterizedTest(name = "given the startDate=''{1}'', then invalidate with the errorMessage=''{2}''")
    @CsvSource(delimiter = '|', textBlock = """
                    startDate | 2050-01-01 | this value must be between past and present time
            """)
    @DisplayName("Given an invalid start date value, it should produce a constraint violation")
    void should_throw_constraint_violation_for_invalid_start_date_value(String attribute, @JavaTimeConversionPattern("yyyy-MM-dd") LocalDate value, String errorMessage) {
        // Given
        ProjectRequest request = new ProjectRequest(
                "NextGen",
                "The NextGen project is a revolutionary new system that combines advanced artificial intelligence with cutting-edge robotics to create a fully automated solution for a variety of industries.",
                value,
                "https://nextgenproject.com",
                "https://github.com/nextgenproject/repository",
                "https://nextgenproject.com/static/preview.jpg"
        );

        // When
        Set<ConstraintViolation<ProjectRequest>> violations = validator.validate(request);

        // Then
        assertThat(violations).hasSize(1);

        // And
        ConstraintViolation<ProjectRequest> violation = violations.iterator().next();
        assertThat(violation.getPropertyPath().toString()).hasToString(attribute);
        assertThat(violation.getMessage()).isEqualTo(errorMessage);
    }

    @ParameterizedTest(name = "given the website=''{1}'', then invalidate with the errorMessage=''{2}''")
    @CsvSource(delimiter = '|', textBlock = """
                    website | nextgenproject.com | this value is not a valid URL. It must contain an HTTPS protocol
                    website | https://a.i        | this value must be between 12 and 255 characters
            """)
    @DisplayName("Given an invalid website value, it should produce a constraint violation")
    void should_throw_constraint_violation_for_invalid_website_value(String attribute, String value, String errorMessage) {
        // Given
        ProjectRequest request = new ProjectRequest(
                "NextGen",
                "The NextGen project is a revolutionary new system that combines advanced artificial intelligence with cutting-edge robotics to create a fully automated solution for a variety of industries.",
                LocalDate.of(2020, 1, 1),
                value,
                "https://github.com/nextgenproject/repository",
                "https://nextgenproject.com/static/preview.jpg"
        );

        // When
        Set<ConstraintViolation<ProjectRequest>> violations = validator.validate(request);

        // Then
        assertThat(violations).hasSize(1);

        // And
        ConstraintViolation<ProjectRequest> violation = violations.iterator().next();
        assertThat(violation.getPropertyPath().toString()).hasToString(attribute);
        assertThat(violation.getMessage()).isEqualTo(errorMessage);
    }

    @ParameterizedTest(name = "given the repositoryURL=''{1}'', then invalidate with the errorMessage=''{2}''")
    @CsvSource(delimiter = '|', textBlock = """
                    repositoryURL | nextgenproject.com | this value is not a valid URL. It must contain an HTTPS protocol
                    repositoryURL | https://i.a        | this value must be between 12 and 255 characters
            """)
    @DisplayName("Given an invalid repositoryURL value, it should produce a constraint violation")
    void should_throw_constraint_violation_for_invalid_repository_url_value(String attribute, String value, String errorMessage) {
        // Given
        ProjectRequest request = new ProjectRequest(
                "NextGen",
                "The NextGen project is a revolutionary new system that combines advanced artificial intelligence with cutting-edge robotics to create a fully automated solution for a variety of industries.",
                LocalDate.of(2020, 1, 1),
                "https://nextgenproject.com",
                value,
                "https://nextgenproject.com/static/preview.jpg"
        );

        // When
        Set<ConstraintViolation<ProjectRequest>> violations = validator.validate(request);

        // Then
        assertThat(violations).hasSize(1);

        // And
        ConstraintViolation<ProjectRequest> violation = violations.iterator().next();
        assertThat(violation.getPropertyPath().toString()).hasToString(attribute);
        assertThat(violation.getMessage()).isEqualTo(errorMessage);
    }

    @ParameterizedTest(name = "given the previewImageURL=''{1}'', then invalidate with the errorMessage=''{2}''")
    @CsvSource(delimiter = '|', textBlock = """
                    previewImageURL | nextgenproject.com | this value is not a valid URL. It must contain an HTTPS protocol
                    previewImageURL | https://i.a        | this value must be between 12 and 255 characters
            """)
    @DisplayName("Given an invalid previewImageURL value, it should produce a constraint violation")
    void should_throw_constraint_violation_for_invalid_preview_image_url_value(String attribute, String value, String errorMessage) {
        // Given
        ProjectRequest request = new ProjectRequest(
                "NextGen",
                "The NextGen project is a revolutionary new system that combines advanced artificial intelligence with cutting-edge robotics to create a fully automated solution for a variety of industries.",
                LocalDate.of(2020, 1, 1),
                "https://nextgenproject.com",
                "https://github.com/nextgenproject/repository",
                value
        );

        // When
        Set<ConstraintViolation<ProjectRequest>> violations = validator.validate(request);

        // Then
        assertThat(violations).hasSize(1);

        // And
        ConstraintViolation<ProjectRequest> violation = violations.iterator().next();
        assertThat(violation.getPropertyPath().toString()).hasToString(attribute);
        assertThat(violation.getMessage()).isEqualTo(errorMessage);
    }

    @Test
    @DisplayName("Given a valid request, it should not produce any constraint violations")
    void should_validate_request_successfully() {
        // Given
        ProjectRequest request = new ProjectRequest(
                "NextGen",
                "The NextGen project is a revolutionary new system that combines advanced artificial intelligence with cutting-edge robotics to create a fully automated solution for a variety of industries.",
                LocalDate.of(2020, 1, 1),
                "https://nextgenproject.com",
                "https://github.com/nextgenproject/repository",
                "https://nextgenproject.com/static/preview.jpg"
        );

        // When
        Set<ConstraintViolation<ProjectRequest>> violations = validator.validate(request);

        // Then
        assertThat(violations).isEmpty();
    }
}
