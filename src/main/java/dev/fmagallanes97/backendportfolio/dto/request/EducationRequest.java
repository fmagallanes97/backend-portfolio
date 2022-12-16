package dev.fmagallanes97.backendportfolio.dto.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.time.LocalDate;

public record EducationRequest(
        @Size(min = 4, max = 45, message = "this value must be between 4 and 45 characters")
        @NotEmpty(message = "this attribute is mandatory")
        String degree,
        @Size(min = 4, max = 45, message = "this value must be between 4 and 45 characters")
        @NotEmpty(message = "this attribute is mandatory")
        String school,
        @Size(min = 4, max = 45, message = "this value must be between 4 and 45 characters")
        @NotEmpty(message = "this attribute is mandatory")
        String academicField,
        @PastOrPresent(message = "this value must be between past and present time")
        @NotNull(message = "this attribute is mandatory")
        LocalDate startDate,
        @PastOrPresent(message = "this value must be between past and present time")
        LocalDate endDate
) {
}