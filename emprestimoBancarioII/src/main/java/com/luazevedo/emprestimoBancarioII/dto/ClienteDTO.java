package com.luazevedo.emprestimoBancarioII.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDTO {

    @NotNull(message = "O ID não pode ser nulo")
    @JsonProperty("id")
    Long id;

    @NotBlank(message = "O nome não pode ser vazio")
    String nome;

    @NotBlank(message = "O email não pode ser vazio")
    String email;

    @NotBlank(message = "O telefone não pode ser vazio")
    String telefone;

}
