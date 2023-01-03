package dev.fmagallanes97.backendportfolio.model;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "education")
public class Education {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String degree;
    private String school;
    @Column(name = "academic_field")
    private String academicField;
    @Column(name = "start_date")
    private LocalDate startDate;
    @Column(name = "end_date")
    private LocalDate endDate;
    @ManyToOne
    @JoinColumn(name = "resume_id")
    private Resume resume;
}
