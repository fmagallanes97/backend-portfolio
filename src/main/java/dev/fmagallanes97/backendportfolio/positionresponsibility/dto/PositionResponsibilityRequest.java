package dev.fmagallanes97.backendportfolio.positionresponsibility.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
public class PositionResponsibilityRequest {
    @Size(min = 4, max = 255, message = "this value must be between 4 and 255 characters")
    @NotEmpty(message = "this attribute is mandatory")
    private String description;
}
