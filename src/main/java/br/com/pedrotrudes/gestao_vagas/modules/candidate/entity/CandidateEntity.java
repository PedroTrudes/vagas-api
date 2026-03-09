package br.com.pedrotrudes.gestao_vagas.modules.candidate.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "candidates")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CandidateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotNull
    private String name;

    @NotBlank
    @Pattern(regexp = "\\S+", message = "O campo [username] não deve conter espaco")
    private String username;

    @NotNull
    @Email(message = "O campo [email] deve ter um email valido")
    @Column(unique = true)
    private String email;

    @NotNull
    @Length(min = 10, max = 100, message = "A senha deve conter entre 10 a 100 caracteres.")
    private String password;

    private String description;
    private String curriculum;

    @CreationTimestamp
    private LocalDateTime createdAt;

    public void updateFrom(CandidateEntity candidate){
        if(candidate.getName() != null){
            this.name = candidate.getName();
        }
        if(candidate.getUsername() != null){
            this.username = candidate.getUsername();
        }
        if(candidate.getPassword() != null){
            this.password = candidate.getPassword();
        }
        if(candidate.getDescription() != null){
            this.description = candidate.getDescription();
        }
        if(candidate.getCurriculum() != null){
            this.curriculum = candidate.getCurriculum();
        }
    }

}
