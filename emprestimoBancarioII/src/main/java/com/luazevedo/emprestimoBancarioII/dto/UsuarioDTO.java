package com.luazevedo.emprestimoBancarioII.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Representa os dados de um usuário para transferência.")
public class UsuarioDTO {

    @JsonProperty
    @NotNull(message = "O ID não pode ser nulo")
    @Schema(description ="Identificador único do usuário.")
    private Long id;

    @NotBlank(message = "O nome não pode ser vazio")
    @Schema(description = "Nome do usuário único.")
    String username;

    @NotBlank(message = "A senha não pode ser vazia")
    @Schema(description = "Senha do usuário.")
    String senha;

    @JsonProperty
    @Schema(description = "Lista de roles associados ao cliente")
    private List<RoleDTO> roles;

}
