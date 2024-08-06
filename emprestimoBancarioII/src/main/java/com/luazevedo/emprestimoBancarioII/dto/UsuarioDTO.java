package com.luazevedo.emprestimoBancarioII.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {

    @NotNull(message = "O ID não pode ser nulo")
    Long id;

    @NotBlank(message = "O nome não pode ser vazio")
    String username;

    @NotBlank(message = "A senha não pode ser vazia")
    String senha;
}
