package dev.fmagallanes97.backendportfolio.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "resume")
public class Resume {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    private String headline;
    private String about;
    @OneToOne(mappedBy = "resume", orphanRemoval = true, cascade = CascadeType.ALL)
    private Contact contact;
    @OneToMany(mappedBy = "resume", orphanRemoval = true, cascade = CascadeType.ALL)
    private Set<Education> education = new HashSet<>();
    @OneToMany(mappedBy = "resume", orphanRemoval = true, cascade = CascadeType.ALL)
    private Set<Position> positions = new HashSet<>();
    @OneToMany(mappedBy = "resume", orphanRemoval = true, cascade = CascadeType.ALL)
    private Set<Project> projects = new HashSet<>();
    @OneToMany(mappedBy = "resume", orphanRemoval = true, cascade = CascadeType.ALL)
    private Set<Skill> skills = new HashSet<>();

    public void addContact(Contact contact) {
        this.contact = contact;
        contact.setResume(this);
    }

    public void removeContact(Contact contact) {
        this.contact = null;
        contact.setResume(null);
    }

    public void addEducation(Education education) {
        this.education.add(education);
        education.setResume(this);
    }

    public void removeEducation(Education education) {
        this.education.remove(education);
        education.setResume(null);
    }

    public void addPosition(Position position) {
        positions.add(position);
        position.setResume(this);
    }

    public void removePosition(Position position) {
        positions.remove(position);
        position.setResume(null);
    }

    public void addProject(Project project) {
        projects.add(project);
        project.setResume(this);
    }

    public void removeProject(Project project) {
        projects.remove(project);
        project.setResume(this);
    }

    public void addSkill(Skill skill) {
        skills.add(skill);
        skill.setResume(this);
    }

    public void removeSkill(Skill skill) {
        skills.remove(skill);
        skill.setResume(null);
    }
}
