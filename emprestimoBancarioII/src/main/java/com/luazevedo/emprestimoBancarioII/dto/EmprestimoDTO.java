package com.luazevedo.emprestimoBancarioII.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "Representa um empréstimo no sistema.")
public class EmprestimoDTO {

    @JsonProperty
    @NotNull(message = "O ID não pode ser nulo")
    @Schema(description = "Identificador único do empréstimo.", example = "1", required = true)
    private Long id;

    @NotNull(message = "O valor não pode ser nulo")
    @Schema(description = "Valor total do empréstimo.", example = "10000.00", required = true)
    private BigDecimal valor;

    @Schema(description = "Data em que o empréstimo foi solicitado.")
    private LocalDate dataEmprestimo;

    @Schema(description = "Data em que o empréstimo termina.")
    private LocalDate dataTermino;

    @NotNull(message = "A taxa de juros não pode ser nula")
    @Schema(description = "Taxa de juros aplicada ao emprréstimo.", example = "5.0", required = true)
    private BigDecimal taxaJuros;

    @NotNull(message = "O prazo em meses não pode ser nulo")
    @Schema(description = "Prazo em meses do pagamento.", example = "12", required = true)
    private Integer prazoMeses;

    @NotNull(message = "O número de parcelas não pode ser nulo")
    @Schema(description = "Número de parcelas do pagamento.", example = "12", required = true)
    private Integer numeroParcelas;

    @Schema(description = "Valor de cada parcela.")
    private BigDecimal parcela;

    @Schema(description = "Valor total a ser pago, incluindo juros.")
    private BigDecimal valorTotal;

    @NotNull(message = "O cliente não pode ser nulo")
    @Schema(description = "Cliente associado ao empréstimo.", required = true)
    private ClienteDTO cliente;
}
