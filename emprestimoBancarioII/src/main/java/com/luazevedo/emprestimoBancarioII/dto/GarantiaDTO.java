package com.luazevedo.emprestimoBancarioII.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GarantiaDTO {

    @JsonProperty
    @NotNull(message = "O ID não pode ser nulo")
    Long id;

    @NotBlank(message = "O campo descrição não pode ser vazio")
    String descricao;

    @NotBlank(message = "O valor não pode ser vazio")
    BigDecimal valor;
}
