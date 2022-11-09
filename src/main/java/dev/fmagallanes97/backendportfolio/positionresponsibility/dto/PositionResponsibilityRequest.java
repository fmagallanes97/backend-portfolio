package dev.fmagallanes97.backendportfolio.positionresponsibility.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class PositionResponsibilityRequest {
    @NotEmpty(message = "Description attribute is mandatory.")
    private String description;
}
