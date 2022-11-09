package dev.fmagallanes97.backendportfolio.education.dto;

import dev.fmagallanes97.backendportfolio.resume.Resume;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class EducationResponse {
    private Long id;
    private String degree;
    private String school;
    private String academicField;
    private LocalDate startDate;
    private LocalDate endDate;
    private Resume resume;
}
