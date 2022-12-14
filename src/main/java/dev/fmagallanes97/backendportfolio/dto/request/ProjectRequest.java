package dev.fmagallanes97.backendportfolio.dto.request;

import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.time.LocalDate;

public record ProjectRequest(
        @Size(min = 4, max = 45, message = "this value must be between 4 and 45 characters")
        @NotEmpty(message = "this attribute is mandatory")
        String title,
        @Size(min = 4, max = 255, message = "this value must be between 4 and 255 characters")
        @NotEmpty(message = "this attribute is mandatory")
        String description,
        @PastOrPresent(message = "this value must be between past and present time")
        LocalDate startDate,
        @Size(min = 4, max = 255, message = "this value must be between 4 and 255 characters")
        @URL(message = "this value is not a valid URL")
        String website,
        @Size(min = 4, max = 255, message = "this value must be between 4 and 255 characters")
        @URL(message = "this value is not a valid URL")
        String repositoryURL,
        @Size(min = 4, max = 255, message = "this value must be between 4 and 255 characters")
        @URL(message = "this value is not a valid URL")
        String previewImageURL
) {
}
