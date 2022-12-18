package dev.fmagallanes97.backendportfolio.dto.mapper;

import dev.fmagallanes97.backendportfolio.model.Contact;
import dev.fmagallanes97.backendportfolio.dto.request.ContactRequest;
import dev.fmagallanes97.backendportfolio.dto.response.ContactResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ContactMapper extends RequestMapper<Contact, ContactRequest>, ResponseMapper<Contact, ContactResponse> {
}
