package com.luazevedo.emprestimoBancarioII.entity;

import com.luazevedo.emprestimoBancarioII.entity.enums.StatusPagamento;
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
@Table(name = "pagamento")
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private BigDecimal valor;

    @Column(nullable = false)
    private BigDecimal taxaJuros;

    @Column(nullable = false)
    private Integer prazoMeses;

    @Column(nullable = false)
    private LocalDate dataPagamento;

    @ManyToOne
    @JoinColumn(name = "emprestimo_id", nullable = false)
    private Emprestimo emprestimo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusPagamento status;

    @Column(nullable = false)
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
