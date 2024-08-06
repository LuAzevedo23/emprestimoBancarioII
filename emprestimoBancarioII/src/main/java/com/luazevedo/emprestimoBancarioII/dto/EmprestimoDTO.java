package com.luazevedo.emprestimoBancarioII.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmprestimoDTO {

    @NotNull(message = "O ID não pode ser nulo")
    private Long id;

    @NotNull(message = "O valor não pode ser nulo")
    private BigDecimal valor;

    @NotNull(message = "A data do empréstimo não pode ser nula")
    private LocalDate dataEmprestimo;

    @NotNull(message = "A data de término não pode ser nula")
    private LocalDate dataTermino;

    private BigDecimal taxaJuros;
}