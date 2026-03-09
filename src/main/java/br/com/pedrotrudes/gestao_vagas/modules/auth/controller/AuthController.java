package br.com.pedrotrudes.gestao_vagas.modules.auth.controller;

import br.com.pedrotrudes.gestao_vagas.modules.auth.dto.AuthRequestDTO;
import br.com.pedrotrudes.gestao_vagas.modules.auth.dto.AuthResponseDTO;
import br.com.pedrotrudes.gestao_vagas.modules.auth.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody AuthRequestDTO request){
        var response = authService.login(request);

        return ResponseEntity.ok(response);
    }
}
