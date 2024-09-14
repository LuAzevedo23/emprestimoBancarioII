package com.luazevedo.emprestimoBancarioII.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.luazevedo.emprestimoBancarioII.entity.Emprestimo;
import com.luazevedo.emprestimoBancarioII.entity.enums.StatusPagamento;
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

    //@NotBlank ou @NotNull: @NotBlank é usado para Strings, enquanto @NotNull é mais apropriado
    // para tipos numéricos e objetos.

    @JsonProperty
    @NotNull(message = "O ID não pode ser nulo")
    private Long id;

    @NotNull(message = "O valor não pode ser nulo")
    private BigDecimal valor;

    @NotNull(message = "A data de pagamento não pode ser nula")
    private LocalDate dataPagamento;

    @NotNull(message = "O campo empréstimo não pode ser nulo")
    private Long emprestimoId;

    @NotNull(message = "O status não pode ser nulo")
    private StatusPagamento status;

    // Esse campo é para a lógica de calcular juros
    @NotNull(message = "O valor principal não pode ser nulo")
    private BigDecimal valorPrincipal;

    @NotNull(message = "A taxa de juros não pode ser nula")
    private BigDecimal taxaJuros;

    @NotNull(message = "O prazo em meses não pode ser nulo")
    private Integer prazoMeses;

    @NotNull(message = "O valor total não pode ser nulo")
    private BigDecimal valorTotal;

}


