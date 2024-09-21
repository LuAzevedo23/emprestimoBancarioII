package com.luazevedo.emprestimoBancarioII.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Representa um papel (role) atribuído a um usuário no sistema.")
public class RoleDTO {

    @JsonProperty@NotNull(message = "O ID não pode ser nulo")
    @Schema(description = "Identificador único do papel (role).", example = "1", required = true)
    private Long id;

    @NotBlank(message = "O nome não pode ser vazio")
    @Schema(description = "Nome do papel (role) atribuído ao usuário.", example = "ROLE_USER", required = true)
    private String nome;
}
