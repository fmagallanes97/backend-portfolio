package dev.fmagallanes97.backendportfolio.model;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "project")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    @Column(name = "start_date")
    private LocalDate startDate;
    private String website;
    @Column(name = "repository_url")
    private String repositoryURL;
    @Column(name = "preview_image_url")
    private String previewImageURL;
    @ManyToOne
    @JoinColumn(name = "resume_id")
    private Resume resume;
}
