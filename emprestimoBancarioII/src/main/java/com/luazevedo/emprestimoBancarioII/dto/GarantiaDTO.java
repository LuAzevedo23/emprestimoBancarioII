package com.luazevedo.emprestimoBancarioII.dto;

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

    @NotNull(message = "O ID não pode ser nulo")
    private Long id;

    @NotBlank(message = "O campo descrição não pode ser vazio")
    private String descricao;

    @NotBlank(message = "O valor não pode ser vazio")
    private BigDecimal valor;
}
