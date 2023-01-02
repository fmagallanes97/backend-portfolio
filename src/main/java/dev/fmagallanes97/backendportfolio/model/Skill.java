package dev.fmagallanes97.backendportfolio.model;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "skill")
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToOne
    @JoinColumn(name = "type_id")
    private SkillType type;
    @ManyToOne
    @JoinColumn(name = "resume_id")
    private Resume resume;
}
