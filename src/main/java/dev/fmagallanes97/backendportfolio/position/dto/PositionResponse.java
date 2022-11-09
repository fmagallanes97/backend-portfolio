package dev.fmagallanes97.backendportfolio.position.dto;

import dev.fmagallanes97.backendportfolio.positionresponsibility.Responsibility;
import dev.fmagallanes97.backendportfolio.resume.Resume;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class PositionResponse {
    private Long id;
    private String role;
    private String companyName;
    private LocalDate startDate;
    private LocalDate endDate;
    private List<Responsibility> responsibilities;
    private Resume resume;
}
