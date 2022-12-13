package dev.fmagallanes97.backendportfolio.dto.mapper;

import dev.fmagallanes97.backendportfolio.model.SkillType;
import dev.fmagallanes97.backendportfolio.dto.SkillTypeRequest;
import dev.fmagallanes97.backendportfolio.dto.SkillTypeResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SkillTypeMapper extends RequestMapper<SkillType, SkillTypeRequest>, ResponseMapper<SkillType, SkillTypeResponse> {
    List<SkillTypeResponse> toResponseList(List<SkillType> types);
}
