package dev.fmagallanes97.backendportfolio.positionresponsibility.dto;

import dev.fmagallanes97.backendportfolio.position.Position;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PositionResponsibilityResponse {
    private Long id;
    private String description;
    private Position position;
}
