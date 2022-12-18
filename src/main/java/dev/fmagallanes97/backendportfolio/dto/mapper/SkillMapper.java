package dev.fmagallanes97.backendportfolio.dto.mapper;

import dev.fmagallanes97.backendportfolio.model.Skill;
import dev.fmagallanes97.backendportfolio.dto.request.SkillRequest;
import dev.fmagallanes97.backendportfolio.dto.response.SkillResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {SkillTypeMapper.class})
public interface SkillMapper extends RequestMapper<Skill, SkillRequest>, ResponseMapper<Skill, SkillResponse> {
    List<SkillResponse> toResponseList(List<Skill> skills);
}
