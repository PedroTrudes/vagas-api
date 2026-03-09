package br.com.pedrotrudes.gestao_vagas.modules.candidate.dto;

import java.util.UUID;

public record CandidateResponseDTO(UUID id, String name, String username, String email, String description, String curriculum) {



}
