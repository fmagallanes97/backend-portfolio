package dev.fmagallanes97.backendportfolio.education.dto;

import dev.fmagallanes97.backendportfolio.resume.Resume;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Getter
@Setter
public class EducationRequest {
    @NotEmpty(message = "Degree attribute is mandatory.")
    private String degree;
    @NotEmpty(message = "School attribute is mandatory.")
    private String school;
    @NotEmpty(message = "Academic field attribute is mandatory.")
    private String academicField;
    @NotEmpty(message = "Start date attribute is mandatory.")
    private LocalDate startDate;
    private LocalDate endDate;
}
