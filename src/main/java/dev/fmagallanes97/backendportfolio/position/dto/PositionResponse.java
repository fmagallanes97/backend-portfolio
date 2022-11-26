package dev.fmagallanes97.backendportfolio.position.dto;

import dev.fmagallanes97.backendportfolio.positionresponsibility.PosResponsibility;

import java.time.LocalDate;
import java.util.List;

public record PositionResponse(
        Long id,
        String role,
        String companyName,
        LocalDate startDate,
        LocalDate endDate,
        List<PosResponsibility> responsibilities
) {
}
