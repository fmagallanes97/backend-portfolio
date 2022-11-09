package dev.fmagallanes97.backendportfolio.project.dto;

import dev.fmagallanes97.backendportfolio.resume.Resume;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ProjectResponse {
    private Long id;
    private String title;
    private String description;
    private LocalDate startDate;
    private String website;
    private String githubRepository;
    private String previewImage;
    private Resume resume;
}
