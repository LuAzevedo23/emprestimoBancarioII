package com.luazevedo.emprestimoBancarioII.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Entidade que representa um empréstimo no sistema.
 * <p>
 * Mapeia a tabela de empréstimos no banco de dados.
 * </p>
 * @author Luciene Azevedo
 */
@Data
@Entity
@Table(name = "emprestimos")
public class Emprestimo {

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
    private Integer numeroParcelas;

    @Column(nullable = false)
    private BigDecimal parcela;

    @Column(nullable = false)
    private LocalDate dataEmprestimo;

    @Column(nullable = false)
    private LocalDate dataTermino;

    @Column(nullable = false)
    private BigDecimal valorTotal;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente; // Cliente associado ao empréstimo

    /**
     * Construtor para criação de um empréstimo com todos os campos obrigatórios.
     *
     * @param valor          o valor do empréstimo
     * @param taxaJuros      a taxa de juros aplicada ao empréstimo
     * @param prazoMeses     o prazo do empréstimo em meses
     * @param numeroParcelas o número de parcelas
     * @param parcela        o valor de cada parcela
     * @param dataEmprestimo a data em que o empréstimo foi realizado
     * @param dataTermino    a data de término do empréstimo
     * @param valorTotal     o valor total do empréstimo
     */

    public Emprestimo(BigDecimal valor, BigDecimal taxaJuros, Integer prazoMeses, Integer numeroParcelas,
                      BigDecimal parcela, LocalDate dataEmprestimo, LocalDate dataTermino,
                      BigDecimal valorTotal) {

        this.valor = valor;
        this.taxaJuros = taxaJuros;
        this.prazoMeses = prazoMeses;
        this.numeroParcelas = numeroParcelas;
        this.parcela = parcela;
        this.dataEmprestimo = dataEmprestimo;
        this.dataTermino = dataTermino;
        this.valorTotal = valorTotal;
    }

    public Emprestimo() {

    }

    /**
     * //     * Método de callback para validação antes de persistir ou atualizar no banco de dados
     * //     *
     * //     * @throws IllegalArgumentException se o prazo em meses for nulo ou menor ou igual a zero.
     * //
     */
    @PrePersist
    @PreUpdate
    public void validate() {
        if (prazoMeses == null || prazoMeses <= 0) {
            throw new IllegalArgumentException("O prazo em meses deve ser definido e maior que zero antes de salvar o empréstimo.");
        }
    }

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

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}





