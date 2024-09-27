package com.luazevedo.emprestimoBancarioII.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Representa os dados necessários para criar um novo usuário.")
public class UsuarioCreateDTO {

    @NotBlank(message = "O nome não pode ser vazio")
    @Schema(description = "Nome do usuário único.", example = "novo_usuario", required = true)
    private String username;

    @NotBlank(message = "A senha não pode ser vazia")
    @Schema(description = "Senha do usuário.", example = "senha123", required = true)
    private String senha;

    @JsonProperty
    @Schema(description = "Lista de roles associados ao usuário.")
    private List<RoleDTO> roles;

}
