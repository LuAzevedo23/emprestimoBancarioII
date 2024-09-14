package com.luazevedo.emprestimoBancarioII.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.luazevedo.emprestimoBancarioII.entity.HistoricoEmprestimo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDate;

/**
 * Data Transfer Object para representar um histórico de empréstimo.
 * Contém as informações necessárias para transferir dados de histórico de empréstimos entre camadas.
 *
 * @see HistoricoEmprestimo
 *
 * @author Luciene Azevedo
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HistoricoEmprestimoDTO {

    @JsonProperty
    @NotNull(message = "O ID não pode ser nulo")
    Long id;

    @NotBlank(message = "A data do evento não pode ser vazio")
    LocalDate dataEvento;

    @NotBlank(message = "O campo descrição não pode ser vazio")
    String descricao;
}
