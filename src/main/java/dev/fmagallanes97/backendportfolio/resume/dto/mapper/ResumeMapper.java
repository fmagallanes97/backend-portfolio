package dev.fmagallanes97.backendportfolio.resume.dto.mapper;

import dev.fmagallanes97.backendportfolio.resume.Resume;
import dev.fmagallanes97.backendportfolio.resume.dto.ResumeRequest;
import dev.fmagallanes97.backendportfolio.resume.dto.ResumeResponse;
import dev.fmagallanes97.backendportfolio.shared.mapper.RequestMapper;
import dev.fmagallanes97.backendportfolio.shared.mapper.ResponseMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ResumeMapper extends RequestMapper<Resume, ResumeRequest>, ResponseMapper<Resume, ResumeResponse> {
}
