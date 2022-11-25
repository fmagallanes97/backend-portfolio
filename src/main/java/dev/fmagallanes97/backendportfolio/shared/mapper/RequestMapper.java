package dev.fmagallanes97.backendportfolio.shared.mapper;

import org.mapstruct.MappingTarget;

public interface RequestMapper<E, R> {
    E toEntity(R request);
    void update(R request, @MappingTarget E entity);
}
