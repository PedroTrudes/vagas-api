package br.com.pedrotrudes.gestao_vagas.modules.auth.service;

import br.com.pedrotrudes.gestao_vagas.modules.auth.dto.AuthRequestDTO;
import br.com.pedrotrudes.gestao_vagas.modules.auth.dto.AuthResponseDTO;
import br.com.pedrotrudes.gestao_vagas.modules.auth.security.JwtService;
import br.com.pedrotrudes.gestao_vagas.modules.candidate.repository.CandidateRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final CandidateRepository candidateRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(CandidateRepository candidateRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.candidateRepository = candidateRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public AuthResponseDTO login(AuthRequestDTO req){

        var candidate = candidateRepository.findByUsername(req.username())
                .orElseThrow(() -> new RuntimeException("Usuario ou senha invalidos"));


        boolean passwordMatches = passwordEncoder.matches(req.password(), candidate.getPassword());

        if(!passwordMatches){
            throw new RuntimeException("Usuario ou senha invalidos");
        }

        String token = jwtService.generateToken(candidate.getUsername());

        return new AuthResponseDTO(token);

    }
}
