package dev.fmagallanes97.backendportfolio.repository;

import dev.fmagallanes97.backendportfolio.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact, Long> {
}
