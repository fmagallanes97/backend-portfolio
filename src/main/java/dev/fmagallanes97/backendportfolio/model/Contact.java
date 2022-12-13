package dev.fmagallanes97.backendportfolio.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "contact")
public class Contact {
    @Id
    private Long id;
    private String email;
    @Column(name = "github_profile_url")
    private String githubProfileURL;
    @Column(name = "linkedin_profile_url")
    private String linkedinProfileURL;
    @OneToOne
    @MapsId
    @JoinColumn(name = "resume_id")
    private Resume resume;
}
