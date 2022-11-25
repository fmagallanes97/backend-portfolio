package dev.fmagallanes97.backendportfolio.position.dto.mapper;

import dev.fmagallanes97.backendportfolio.position.Position;
import dev.fmagallanes97.backendportfolio.position.dto.PositionRequest;
import dev.fmagallanes97.backendportfolio.position.dto.PositionResponse;
import dev.fmagallanes97.backendportfolio.shared.mapper.RequestMapper;
import dev.fmagallanes97.backendportfolio.shared.mapper.ResponseMapper;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PositionMapper extends RequestMapper<Position, PositionRequest>, ResponseMapper<Position, PositionResponse> {
    List<PositionResponse> toResponseList(List<Position> positions);
}
