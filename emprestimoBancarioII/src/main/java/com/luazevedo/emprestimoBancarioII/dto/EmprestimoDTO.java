package com.luazevedo.emprestimoBancarioII.dto;

import jakarta.validation.constraints.NotBlank;
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

    @NotBlank(message = "O valor não pode ser vazio")
    private BigDecimal valor;

    @NotBlank(message = "A data do empréstimo não pode ser vazio")
    private LocalDate dataEmprestimo;

    @NotBlank(message = "A data de término não pode ser vazio")
    private LocalDate dataTermino;
}
