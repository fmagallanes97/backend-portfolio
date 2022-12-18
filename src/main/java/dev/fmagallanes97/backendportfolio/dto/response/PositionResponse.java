package dev.fmagallanes97.backendportfolio.dto.response;

import java.time.LocalDate;
import java.util.List;

public record PositionResponse(
        Long id,
        String role,
        String companyName,
        LocalDate startDate,
        LocalDate endDate,
        List<PosResponsibilityResponse> responsibilities
) {
}
