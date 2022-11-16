package dev.fmagallanes97.backendportfolio.skilltype.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
public class SkillTypeRequest {
    @Size(min = 2, max = 45, message = "this value must be between 2 and 45 characters")
    @NotEmpty(message = "this attribute is mandatory")
    private String name;
}
