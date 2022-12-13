package dev.fmagallanes97.backendportfolio.dto.mapper;

public interface ResponseMapper<E, R> {
    R toResponse(E entity);
}
