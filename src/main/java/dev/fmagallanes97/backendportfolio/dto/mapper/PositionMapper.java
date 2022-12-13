package dev.fmagallanes97.backendportfolio.dto.mapper;

import dev.fmagallanes97.backendportfolio.model.Position;
import dev.fmagallanes97.backendportfolio.dto.PositionRequest;
import dev.fmagallanes97.backendportfolio.dto.PositionResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {PosResponsibilityMapper.class})
public interface PositionMapper extends RequestMapper<Position, PositionRequest>, ResponseMapper<Position, PositionResponse> {
    List<PositionResponse> toResponseList(List<Position> positions);
}
