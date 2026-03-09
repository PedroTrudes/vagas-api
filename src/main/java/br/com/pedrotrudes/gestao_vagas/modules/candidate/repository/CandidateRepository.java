package br.com.pedrotrudes.gestao_vagas.modules.candidate.repository;

import br.com.pedrotrudes.gestao_vagas.modules.candidate.entity.CandidateEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CandidateRepository extends JpaRepository<CandidateEntity, UUID> {

    Optional<CandidateEntity> findByEmail(String email);

    Optional<CandidateEntity> findByUsername(String username);
}
