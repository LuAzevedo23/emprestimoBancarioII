package com.luazevedo.emprestimoBancarioII.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HistoricoEmprestimoDTO {

    @NotNull(message = "O ID não pode ser nulo")
    Long id;

    @NotBlank(message = "A data do evento não pode ser vazio")
    Instant dataEvento;

    @NotBlank(message = "O campo descrição não pode ser vazio")
    String descricao;
}
