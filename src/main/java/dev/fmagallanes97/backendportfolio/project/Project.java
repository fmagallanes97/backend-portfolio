package dev.fmagallanes97.backendportfolio.project;

import dev.fmagallanes97.backendportfolio.resume.Resume;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
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
    @Column(name = "github_repository")
    private String githubRepository;
    @Column(name = "preview_image")
    private String previewImage;
    @ManyToOne
    @JoinColumn(name = "resume_id")
    private Resume resume;
}
