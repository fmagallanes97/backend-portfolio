package dev.fmagallanes97.backendportfolio.contact.dto.mapper;

import dev.fmagallanes97.backendportfolio.contact.Contact;
import dev.fmagallanes97.backendportfolio.contact.dto.ContactRequest;
import dev.fmagallanes97.backendportfolio.contact.dto.ContactResponse;
import dev.fmagallanes97.backendportfolio.shared.mapper.RequestMapper;
import dev.fmagallanes97.backendportfolio.shared.mapper.ResponseMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ContactMapper extends RequestMapper<Contact, ContactRequest>, ResponseMapper<Contact, ContactResponse> {
}
