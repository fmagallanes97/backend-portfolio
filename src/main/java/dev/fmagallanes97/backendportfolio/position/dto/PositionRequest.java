package dev.fmagallanes97.backendportfolio.position.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Getter
@Setter
public class PositionRequest {
    @NotEmpty(message = "Role attribute is mandatory.")
    private String role;
    @NotEmpty(message = "Company name attribute is mandatory.")
    private String companyName;
    @NotEmpty(message = "Start date attribute is mandatory.")
    private LocalDate startDate;
    private LocalDate endDate;
}
