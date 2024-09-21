package com.luazevedo.emprestimoBancarioII.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Representa uma garantia no sistema.")
public class GarantiaDTO {

    @JsonProperty
    @NotNull(message = "O ID não pode ser nulo")
    @Schema(description = "Identificador único da garantia.", example = "1", required = true)
    Long id;

    @NotBlank(message = "O campo descrição não pode ser vazio")
    @Schema(description = "Descrição da garantia do empréstimo.", required = true)
    String descricao;

    @NotBlank(message = "O valor não pode ser vazio")
    @Schema(description = "Valor da garantia.", example = "500.00", required = true)
    BigDecimal valor;
}
