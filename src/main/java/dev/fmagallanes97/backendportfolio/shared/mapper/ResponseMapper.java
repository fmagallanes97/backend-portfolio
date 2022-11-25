package dev.fmagallanes97.backendportfolio.shared.mapper;

public interface ResponseMapper<E, R> {
    R toResponse(E entity);
}
