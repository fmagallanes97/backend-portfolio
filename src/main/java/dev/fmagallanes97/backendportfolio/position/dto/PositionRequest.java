package dev.fmagallanes97.backendportfolio.position.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Getter
@Setter
public class PositionRequest {
    @Size(min = 4, max = 45, message = "this value must be between 4 and 45 characters")
    @NotEmpty(message = "this attribute is mandatory")
    private String role;
    @Size(min = 4, max = 45, message = "this value must be between 4 and 45 characters")
    @NotEmpty(message = "this attribute is mandatory")
    private String companyName;
    @PastOrPresent(message = "this value must be between past and present time")
    @NotEmpty(message = "this attribute is mandatory")
    private LocalDate startDate;
    private LocalDate endDate;
}
