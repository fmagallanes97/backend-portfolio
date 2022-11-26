package dev.fmagallanes97.backendportfolio.positionresponsibility.dto.mapper;

import dev.fmagallanes97.backendportfolio.positionresponsibility.PosResponsibility;
import dev.fmagallanes97.backendportfolio.positionresponsibility.dto.PosResponsibilityRequest;
import dev.fmagallanes97.backendportfolio.positionresponsibility.dto.PosResponsibilityResponse;
import dev.fmagallanes97.backendportfolio.shared.mapper.RequestMapper;
import dev.fmagallanes97.backendportfolio.shared.mapper.ResponseMapper;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PosResponsibilityMapper extends RequestMapper<PosResponsibility, PosResponsibilityRequest>, ResponseMapper<PosResponsibility, PosResponsibilityResponse> {
    List<PosResponsibilityResponse> toResponseList(List<PosResponsibility> responsibilities);
}
