package com.luazevedo.emprestimoBancarioII.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConfiguracaoDTO {

    @NotNull(message = "O ID não pode ser nulo")
    Long id;

    @NotBlank(message = "A chave não pode ser vazia")
    String chave;

    @NotBlank(message = "O valor não pode ser vazio")
    String valor;
}
