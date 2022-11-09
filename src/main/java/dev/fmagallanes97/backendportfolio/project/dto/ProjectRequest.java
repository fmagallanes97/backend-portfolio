package dev.fmagallanes97.backendportfolio.project.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Getter
@Setter
public class ProjectRequest {
    @NotEmpty(message = "Tittle attribute is mandatory.")
    private String title;
    @NotEmpty(message = "Description attribute is mandatory.")
    private String description;
    private LocalDate startDate;
    private String website;
    private String githubRepository;
    private String previewImage;
}
