package dev.fmagallanes97.backendportfolio.position;

import dev.fmagallanes97.backendportfolio.positionresponsibility.PosResponsibility;
import dev.fmagallanes97.backendportfolio.resume.Resume;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "position")
public class Position {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String role;
    @Column(name = "company_name")
    private String companyName;
    @Column(name = "start_date")
    private LocalDate startDate;
    @Column(name = "end_date")
    private LocalDate endDate;
    @ManyToOne
    @JoinColumn(name = "resume_id")
    private Resume resume;
    @OneToMany(mappedBy = "position", orphanRemoval = true, cascade = CascadeType.ALL)
    private Set<PosResponsibility> responsibilities = new HashSet<>();

    public void addResponsibility(PosResponsibility responsibility) {
        responsibilities.add(responsibility);
        responsibility.setPosition(this);
    }

    public void removeResponsibility(PosResponsibility responsibility) {
        responsibilities.remove(responsibility);
        responsibility.setPosition(null);
    }
}
