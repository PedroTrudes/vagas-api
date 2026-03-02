package br.com.pedrotrudes.gestao_vagas.modules.candidate;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jdk.jfr.Timestamp;
import lombok.Data;
import lombok.NonNull;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity(name = "candidate")
@Table
public class CandidateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @NonNull
    private String name;

    @NotBlank
    @Pattern(regexp = "\\S+", message = "O campo [usarname] não deve conter espaco")
    private String username;

    @NotNull
    @Email(message = "O campo [email] deve ter um email valido")
    private String email;

    @NotNull
    @Length(min = 10, max = 100, message = "A senha deve conter entre 10 a 100 caracteres.")
    private String password;

    private String description;
    private String curriculum;

    @CreationTimestamp
    private LocalDateTime createdAt;

}
