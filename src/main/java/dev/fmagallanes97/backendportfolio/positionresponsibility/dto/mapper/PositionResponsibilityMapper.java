package dev.fmagallanes97.backendportfolio.positionresponsibility.dto.mapper;

import dev.fmagallanes97.backendportfolio.positionresponsibility.PositionResponsibility;
import dev.fmagallanes97.backendportfolio.positionresponsibility.dto.PositionResponsibilityRequest;
import dev.fmagallanes97.backendportfolio.positionresponsibility.dto.PositionResponsibilityResponse;
import dev.fmagallanes97.backendportfolio.shared.mapper.RequestMapper;
import dev.fmagallanes97.backendportfolio.shared.mapper.ResponseMapper;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PositionResponsibilityMapper extends RequestMapper<PositionResponsibility, PositionResponsibilityRequest>, ResponseMapper<PositionResponsibility, PositionResponsibilityResponse> {
    List<PositionResponsibilityResponse> toResponseList(List<PositionResponsibility> responsibilities);
}
