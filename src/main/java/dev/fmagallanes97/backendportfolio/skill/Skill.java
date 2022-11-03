package dev.fmagallanes97.backendportfolio.skill;

import dev.fmagallanes97.backendportfolio.resume.Resume;
import dev.fmagallanes97.backendportfolio.skilltype.SkillType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

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
    @JoinColumn(name = "skill_type_id")
    private SkillType skillType;
    @ManyToOne
    @JoinColumn(name = "resume_id")
    private Resume resume;
}
