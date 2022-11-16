package dev.fmagallanes97.backendportfolio.contact.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
public class ContactRequest {
    @Email(message = "this value is not a valid email")
    @Size(min = 12, max = 45, message = "this value must be between 12 and 45 characters")
    @NotEmpty(message = "this attribute is mandatory")
    private String email;
    private String github;
    private String linkedin;
}
