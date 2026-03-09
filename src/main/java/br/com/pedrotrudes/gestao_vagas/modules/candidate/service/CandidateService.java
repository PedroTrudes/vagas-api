package br.com.pedrotrudes.gestao_vagas.modules.candidate.service;

import br.com.pedrotrudes.gestao_vagas.modules.candidate.entity.CandidateEntity;
import br.com.pedrotrudes.gestao_vagas.modules.candidate.repository.CandidateRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CandidateService {

    private final CandidateRepository candidateRepository;
    private final PasswordEncoder passwordEncoder;

    public CandidateService(CandidateRepository candidateRepository, PasswordEncoder passwordEncoder) {
        this.candidateRepository = candidateRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public CandidateEntity createCandidate(CandidateEntity candidate){
        var candidateExists = candidateRepository.findByEmail(candidate.getEmail());

        if(candidateExists.isPresent()){
            throw new RuntimeException("Candidato já existe");
        }

        String encryptedPassword = passwordEncoder.encode(candidate.getPassword());
        candidate.setPassword(encryptedPassword);

        return candidateRepository.save(candidate);
    }

    public List<CandidateEntity> findAll(){
        return candidateRepository.findAll();
    }

    public CandidateEntity findById(UUID id){
        return candidateRepository.findById(id).orElseThrow(() -> new RuntimeException("Candidato não encontrado"));
    }

    public CandidateEntity updatePartial(UUID id ,CandidateEntity candidate){
        var existingCandidate = candidateRepository.findById(id).orElseThrow(() -> new RuntimeException("Candidato não encontrado"));

        existingCandidate.updateFrom(candidate);

        return candidateRepository.save(existingCandidate);
    }

    public void deleteById(UUID id){
        var candidate = candidateRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuario não existe"));
        candidateRepository.delete(candidate);//Não usamos o DeleteById(id) por conta do retorno dele que não é muito claro.
    }
}