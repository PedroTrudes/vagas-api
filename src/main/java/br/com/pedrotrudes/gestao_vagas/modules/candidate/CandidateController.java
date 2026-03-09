package br.com.pedrotrudes.gestao_vagas.modules.candidate;

import br.com.pedrotrudes.gestao_vagas.modules.candidate.dto.CandidateResponseDTO;
import br.com.pedrotrudes.gestao_vagas.modules.candidate.mapper.CandidateMapper;
import jakarta.websocket.server.PathParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/candidates")
public class CandidateController {

    private final CandidateService candidateService;
    public CandidateController(CandidateService candidateService) {
        this.candidateService = candidateService;
    }

    @PostMapping("/")
    public ResponseEntity<CandidateResponseDTO> create(@RequestBody CandidateEntity candidate){
        var createdCandidate = candidateService.createCandidate(candidate);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(CandidateMapper.toDto(createdCandidate));
    }

    @GetMapping()
    public List<CandidateEntity> getAll(){
        return candidateService.findAll();
    }

    @GetMapping("/{id}")
    public CandidateEntity getById(@PathVariable UUID id){
        return candidateService.findById(id);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CandidateEntity> updatePartial(@PathVariable UUID id, @RequestBody CandidateEntity candidateEntity){
        var updateCandidate = candidateService.updatePartial(id, candidateEntity);
        return ResponseEntity.ok(updateCandidate);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id){
        candidateService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
