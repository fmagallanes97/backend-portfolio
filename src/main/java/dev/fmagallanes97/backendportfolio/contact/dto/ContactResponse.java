package dev.fmagallanes97.backendportfolio.contact.dto;

import dev.fmagallanes97.backendportfolio.resume.Resume;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContactResponse {
    private Long id;
    private String email;
    private String github;
    private String linkedin;
    private Resume resume;
}
