package dev.fmagallanes97.backendportfolio.shared.exception.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class InvalidArgumentResponse {
    private final String name;
    private final String reason;
}
