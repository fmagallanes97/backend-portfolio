package dev.fmagallanes97.backendportfolio.skill.dto.mapper;

import dev.fmagallanes97.backendportfolio.shared.mapper.RequestMapper;
import dev.fmagallanes97.backendportfolio.shared.mapper.ResponseMapper;
import dev.fmagallanes97.backendportfolio.skill.Skill;
import dev.fmagallanes97.backendportfolio.skill.dto.SkillRequest;
import dev.fmagallanes97.backendportfolio.skill.dto.SkillResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SkillMapper extends RequestMapper<Skill, SkillRequest>, ResponseMapper<Skill, SkillResponse> {
    List<SkillResponse> toResponseList(List<Skill> skills);
}
