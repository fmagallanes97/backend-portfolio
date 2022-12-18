package dev.fmagallanes97.backendportfolio.dto.response;

import java.time.LocalDate;

public record ProjectResponse(
        Long id,
        String title,
        String description,
        LocalDate startDate,
        String website,
        String repositoryURL,
        String previewImageURL
) {
}
