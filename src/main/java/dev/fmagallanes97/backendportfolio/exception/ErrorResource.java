package dev.fmagallanes97.backendportfolio.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorResource {
    CONTACT("contact"),
    EDUCATION("education"),
    JOB_POSITION("job position"),
    POS_RESPONSIBILITY("job position responsibility"),
    PROJECT("project"),
    RESUME("resume"),
    SKILL("skill"),
    SKILL_TYPE("skill type");

    private final String name;
}
