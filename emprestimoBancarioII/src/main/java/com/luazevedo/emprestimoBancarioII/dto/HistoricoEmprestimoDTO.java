package com.luazevedo.emprestimoBancarioII.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.luazevedo.emprestimoBancarioII.entity.HistoricoEmprestimo;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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
@Schema(description = "Representa o histórico de empréstimos do cliente.")
public class HistoricoEmprestimoDTO {

    @JsonProperty
    @NotNull(message = "O ID não pode ser nulo")
    @Schema(description = "Identificador único do histórico de empréstimo.", example = "1", required = true)
    Long id;

    @NotBlank(message = "A data do evento não pode ser vazio")
    @Schema(description = "Data em que o evento do empréstimo ocorreu.", example = "2024-09-19", required = true)
    LocalDate dataEvento;

    @NotBlank(message = "O campo descrição não pode ser vazio")
    @Schema(description = "Descrição do histórico do empréstimo.", required = true)
    String descricao;
}
