package com.luazevedo.emprestimoBancarioII.entity;

import com.luazevedo.emprestimoBancarioII.entity.enums.StatusPagamento;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Entidade que representa um pagamento realizado para um empréstimo.
 * Armazena informações sobre o valor pago e a data do pagamento.
 *
 * @author Luciene Azevedo
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pagamentos")
@Schema(description = "Representa o pagamento do empréstimo no sistema")
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único do pagamento.")
    private Long id;

    @Column(nullable = false)
    @Schema(description = "Identificador único do pagamento.", example = "1", required = true)
    private BigDecimal valor;

    @Column(nullable = false)
    @Schema(description = "Taxa de juros aplicada ao pagamento.", example = "5.0", required = true)
    private BigDecimal taxaJuros;

    @Column(nullable = false)
    @Schema(description = "Prazo em meses do pagamento.", example = "12", required = true)
    private Integer prazoMeses;

    @Column(nullable = false)
    @Schema(description = "Data em que o pagamento foi realizado.", example = "2024-09-01", required = true)
    private LocalDate dataPagamento;

    @ManyToOne
    @JoinColumn(name = "emprestimo_id", nullable = false)
    @Schema(description = "Empréstimo associado ao pagamento.")
    private Emprestimo emprestimo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Schema(description = "Status do pagamento.", required = true)
    private StatusPagamento status;

    @Column(nullable = false)
    @Schema(description = "Valor total a ser pago, incluindo juros.", example = "1650.00", required = true)
    private BigDecimal valorTotal;

    /**
     * Construtor para criar um pagamento associado a um empréstimo.
     *
     * @param valor         o valor do pagameno
     * @param taxaJuros     a taxa de juros aplicada
     * @param prazoMeses    o prazo de pagamento em meses
     * @param dataPagamento a data do pagamento
     * @param emprestimo    o emprestimo associado
     * @param status        o status do pagamento
     * @param valorTotal    o valor total do pagamento
     */

    public Pagamento(BigDecimal valor, BigDecimal taxaJuros, Integer prazoMeses, LocalDate dataPagamento,
                     Emprestimo emprestimo, StatusPagamento status, BigDecimal valorTotal) {
        this.valor = valor;
        this.taxaJuros = taxaJuros;
        this.prazoMeses = prazoMeses;
        this.dataPagamento = dataPagamento;
        this.emprestimo = emprestimo;
        this.status = status;
        this.valorTotal = valorTotal;

    }
}

//    @Column(name = "valor", nullable = false)
//    private BigDecimal valor;
//
//    @Column(name = "taxa_juros", nullable = false)
//    private BigDecimal taxaJuros;
//
//    @Column(name = "prazo_meses", nullable = false)
//    private Integer prazoMeses;
//
//    @Column(name = "data_pagamento", nullable = false)
//    private LocalDate dataPagamento;
//
//
//
//    @Enumerated(EnumType.STRING)
//    @Column(name = "status", nullable = false)
//    private StatusPagamento status;
//
//    @Column(name = "valor_total", nullable = false)
//    private BigDecimal valorTotal;
