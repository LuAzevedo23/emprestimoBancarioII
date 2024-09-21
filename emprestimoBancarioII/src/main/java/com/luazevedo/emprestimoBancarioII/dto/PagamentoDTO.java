package com.luazevedo.emprestimoBancarioII.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.luazevedo.emprestimoBancarioII.entity.enums.StatusPagamento;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Representa um pagamento de empréstimo no sistema.")
public class PagamentoDTO {

    //@NotBlank ou @NotNull: @NotBlank é usado para Strings, enquanto @NotNull é mais apropriado
    // para tipos numéricos e objetos.

    @JsonProperty
    @NotNull(message = "O ID não pode ser nulo")
    @Schema(description= "Identificador único do pagamento.", example = "1", required = true)
    private Long id;

    @NotNull(message = "O valor não pode ser nulo")
    @Schema(description= "Valor do pagamento.", example = "1500.00", required = true)
    private BigDecimal valor;

    @NotNull(message = "A data de pagamento não pode ser nula")
    @Schema(description = "Data em que o pagamento foi realizado.", example = "2024-09-01", required = true)
    private LocalDate dataPagamento;

    @NotNull(message = "O campo empréstimo não pode ser nulo")
    @Schema(description = "Identificador do empréstimo associado ao pagamento.", example = "2", required = true)
    private Long emprestimoId;

    @NotNull(message = "O status não pode ser nulo")
    @Schema(description = "Status do pagamento.", required = true)
    private StatusPagamento status;

    // Esse campo é para a lógica de calcular juros
    @NotNull(message = "O valor principal não pode ser nulo")
    @Schema(description = "Valor principal do empréstimo associado.", example = "1000.00", required = true)
    private BigDecimal valorPrincipal;

    @NotNull(message = "A taxa de juros não pode ser nula")
    @Schema(description = "Taxa de juros aplicada ao pagamento.", example = "5.0", required = true)
    private BigDecimal taxaJuros;

    @NotNull(message = "O prazo em meses não pode ser nulo")
    @Schema(description = "Prazo em meses do pagamento.", example = "12", required = true)
    private Integer prazoMeses;

    @NotNull(message = "O valor total não pode ser nulo")
    @Schema(description = "Valor total a ser pago, incluindo juros.", example = "1650.00", required = true)
    private BigDecimal valorTotal;

}


