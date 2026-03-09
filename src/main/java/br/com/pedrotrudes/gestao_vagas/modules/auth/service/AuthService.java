package br.com.pedrotrudes.gestao_vagas.modules.auth.service;

import br.com.pedrotrudes.gestao_vagas.modules.auth.dto.AuthRequestDTO;
import br.com.pedrotrudes.gestao_vagas.modules.auth.dto.AuthResponseDTO;
import br.com.pedrotrudes.gestao_vagas.modules.candidate.repository.CandidateRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final CandidateRepository candidateRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(CandidateRepository candidateRepository, PasswordEncoder passwordEncoder) {
        this.candidateRepository = candidateRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public AuthResponseDTO login(AuthRequestDTO req){
        var candidate = candidateRepository.findByUsername(req.username())
                .orElseThrow(() -> new RuntimeException("Usuario ou senha invalidos"));
        //comparando o password da res com o password incriptado
        boolean passwordMatches = passwordEncoder.matches(req.password(), candidate.getPassword());

        if(!passwordMatches){
            throw new RuntimeException("Usuario ou senha invalidos");
        }

        return new AuthResponseDTO("TOKEN_AQUI");

    }
}
