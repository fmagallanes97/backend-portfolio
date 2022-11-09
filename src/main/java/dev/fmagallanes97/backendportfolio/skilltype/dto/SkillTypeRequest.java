package dev.fmagallanes97.backendportfolio.skilltype.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class SkillTypeRequest {
    @NotEmpty(message = "Name attribute is mandatory.")
    private String name;
}
