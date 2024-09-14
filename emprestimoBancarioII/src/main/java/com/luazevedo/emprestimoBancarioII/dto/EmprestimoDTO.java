package com.luazevedo.emprestimoBancarioII.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Data Transfer Object para representar um empréstimo.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmprestimoDTO {

    @JsonProperty
    @NotNull(message = "O ID não pode ser nulo")
    private Long id;

    @NotNull(message = "O valor não pode ser nulo")
    private BigDecimal valor;

    private LocalDate dataEmprestimo;

    private LocalDate dataTermino;

    @NotNull(message = "A taxa de juros não pode ser nula")
    private BigDecimal taxaJuros;

    @NotNull(message = "O prazo em meses não pode ser nulo")
    private Integer prazoMeses;

    @NotNull(message = "O número de parcelas não pode ser nulo")
    private Integer numeroParcelas;

    private BigDecimal parcela;

    private BigDecimal valorTotal;

    @NotNull(message = "O cliente não pode ser nulo")
    private ClienteDTO cliente;
}
