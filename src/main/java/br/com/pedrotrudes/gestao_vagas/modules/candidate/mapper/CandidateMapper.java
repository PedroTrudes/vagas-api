package br.com.pedrotrudes.gestao_vagas.modules.candidate.mapper;

import br.com.pedrotrudes.gestao_vagas.modules.candidate.entity.CandidateEntity;
import br.com.pedrotrudes.gestao_vagas.modules.candidate.dto.CandidateResponseDTO;

public class CandidateMapper {

    public static CandidateResponseDTO toDto(CandidateEntity entity){
        return new CandidateResponseDTO(
                entity.getId(),
                entity.getName(),
                entity.getUsername(),
                entity.getEmail(),
                entity.getDescription(),
                entity.getCurriculum()
        );
    }
}
