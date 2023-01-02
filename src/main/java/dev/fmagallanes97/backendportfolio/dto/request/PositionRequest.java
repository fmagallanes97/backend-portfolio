package dev.fmagallanes97.backendportfolio.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

public record PositionRequest(
        @Size(min = 4, max = 45, message = "this value must be between 4 and 45 characters")
        @NotEmpty(message = "this attribute is mandatory")
        String role,
        @Size(min = 4, max = 45, message = "this value must be between 4 and 45 characters")
        @NotEmpty(message = "this attribute is mandatory")
        String companyName,
        @PastOrPresent(message = "this value must be between past and present time")
        @NotNull(message = "this attribute is mandatory")
        LocalDate startDate,
        @PastOrPresent(message = "this value must be between past and present time")
        LocalDate endDate
) {
}
