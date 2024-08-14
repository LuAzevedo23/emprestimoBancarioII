package com.luazevedo.emprestimoBancarioII.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Data Transfer Object para representar um empréstimo.
 */
public class EmprestimoDTO {

    /**
     * Identificador único do empréstimo.
     */
    private Long id;

    /**
     * Valor do empréstimo.
     */
    private BigDecimal valor;

    /**
     * Data do empréstimo.
     */
    private LocalDate dataEmprestimo;

    /**
     * Data de término do empréstimo.
     */
    private LocalDate dataTermino;

    /**
     * Taxa de juros aplicada ao empréstimo.
     */
    private BigDecimal taxaJuros;

    /**
     * Prazo do empréstimo em meses.
     */
    private Integer prazoMeses;

    /**
     * Número de parcelas do empréstimo.
     */
    private Integer numeroParcelas;

    /**
     * Valor de cada parcela do empréstimo.
     */
    private BigDecimal parcela;

    /**
     * Valor total do empréstimo após o cálculo dos juros.
     */
    private BigDecimal valorTotal;

    // Construtor vazio
    public EmprestimoDTO() {
    }

    // Construtor com todos os parâmetros
    public EmprestimoDTO(Long id, BigDecimal valor, LocalDate dataEmprestimo, LocalDate dataTermino,
                         BigDecimal taxaJuros, Integer prazoMeses, Integer numeroParcelas,
                         BigDecimal parcela, BigDecimal valorTotal) {
        this.id = id;
        this.valor = valor;
        this.dataEmprestimo = dataEmprestimo;
        this.dataTermino = dataTermino;
        this.taxaJuros = taxaJuros;
        this.prazoMeses = prazoMeses;
        this.numeroParcelas = numeroParcelas;
        this.parcela = parcela;
        this.valorTotal = valorTotal;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(LocalDate dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public LocalDate getDataTermino() {
        return dataTermino;
    }

    public void setDataTermino(LocalDate dataTermino) {
        this.dataTermino = dataTermino;
    }

    public BigDecimal getTaxaJuros() {
        return taxaJuros;
    }

    public void setTaxaJuros(BigDecimal taxaJuros) {
        this.taxaJuros = taxaJuros;
    }

    public Integer getPrazoMeses() {
        return prazoMeses;
    }

    public void setPrazoMeses(Integer prazoMeses) {
        this.prazoMeses = prazoMeses;
    }

    public Integer getNumeroParcelas() {
        return numeroParcelas;
    }

    public void setNumeroParcelas(Integer numeroParcelas) {
        this.numeroParcelas = numeroParcelas;
    }

    public BigDecimal getParcela() {
        return parcela;
    }

    public void setParcela(BigDecimal parcela) {
        this.parcela = parcela;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }
}