package dev.fmagallanes97.backendportfolio.contact.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class ContactRequest {
    @Email
    @NotEmpty(message = "Email attribute is mandatory.")
    private String email;
    private String github;
    private String linkedin;
}
