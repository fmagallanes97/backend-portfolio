package dev.fmagallanes97.backendportfolio.positionresponsibility;

import dev.fmagallanes97.backendportfolio.position.Position;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "position_responsibility")
public class PositionResponsibility {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    @ManyToOne
    @JoinColumn(name = "position_id")
    private Position position;
}
