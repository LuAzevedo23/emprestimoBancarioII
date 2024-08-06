package com.luazevedo.emprestimoBancarioII.dto;

import com.luazevedo.emprestimoBancarioII.entity.Emprestimo;
import com.luazevedo.emprestimoBancarioII.entity.enums.StatusPagamento;
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
public class PagamentoDTO {

    @NotNull(message = "O ID não pode ser nulo")
    Long id;

    @NotBlank(message = "O valor não pode ser vazio")
    BigDecimal valor;

    @NotBlank(message = "A data de pagamento não pode ser vazia")
    LocalDate dataPagamento;

    @NotBlank(message = "O campo emprestimo não pode ser vazio")
    Emprestimo emprestimo;

    @NotBlank(message = "O nome não pode ser vazio")
    StatusPagamento status;

}


