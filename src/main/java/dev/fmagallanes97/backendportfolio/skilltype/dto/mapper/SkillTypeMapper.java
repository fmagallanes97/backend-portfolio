package dev.fmagallanes97.backendportfolio.skilltype.dto.mapper;

import dev.fmagallanes97.backendportfolio.shared.mapper.RequestMapper;
import dev.fmagallanes97.backendportfolio.shared.mapper.ResponseMapper;
import dev.fmagallanes97.backendportfolio.skilltype.SkillType;
import dev.fmagallanes97.backendportfolio.skilltype.dto.SkillTypeRequest;
import dev.fmagallanes97.backendportfolio.skilltype.dto.SkillTypeResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SkillTypeMapper extends RequestMapper<SkillType, SkillTypeRequest>, ResponseMapper<SkillType, SkillTypeResponse> {
    List<SkillTypeResponse> toResponseList(List<SkillType> types);
}
