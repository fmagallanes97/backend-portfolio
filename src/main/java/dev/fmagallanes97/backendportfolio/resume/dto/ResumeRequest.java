package dev.fmagallanes97.backendportfolio.resume.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class ResumeRequest {
    @NotEmpty(message = "First name attribute is mandatory.")
    private String firstName;
    @NotEmpty(message = "Last name attribute is mandatory.")
    private String lastName;
    @NotEmpty(message = "Headline attribute is mandatory.")
    private String headline;
    @NotEmpty(message = "About attribute is mandatory.")
    private String about;
}
