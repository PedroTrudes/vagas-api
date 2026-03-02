package br.com.pedrotrudes.gestao_vagas.modules.candidate.controller;

import br.com.pedrotrudes.gestao_vagas.modules.candidate.CandidateEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CandidateRepository extends JpaRepository<CandidateEntity, UUID> {
    Optional<CandidateEntity> findByUsernameOrEmail(String username, String email);

    //Optional<CandidateEntity> deleteByUsername(String username);
}
