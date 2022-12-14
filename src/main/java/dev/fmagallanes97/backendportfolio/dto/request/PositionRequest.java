package dev.fmagallanes97.backendportfolio.dto.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
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
        LocalDate endDate
) {
}
