package dev.fmagallanes97.backendportfolio.dto.mapper;

import dev.fmagallanes97.backendportfolio.model.PosResponsibility;
import dev.fmagallanes97.backendportfolio.dto.PosResponsibilityRequest;
import dev.fmagallanes97.backendportfolio.dto.PosResponsibilityResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PosResponsibilityMapper extends RequestMapper<PosResponsibility, PosResponsibilityRequest>, ResponseMapper<PosResponsibility, PosResponsibilityResponse> {
    List<PosResponsibilityResponse> toResponseList(List<PosResponsibility> responsibilities);
}
