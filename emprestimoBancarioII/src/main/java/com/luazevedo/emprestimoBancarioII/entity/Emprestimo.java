package com.luazevedo.emprestimoBancarioII.entity;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Entidade que representa um empréstimo.
 * <p>
 * Mapeia a tabela de empréstimos no banco de dados.
 * </p>
 */
@Entity
@Table(name = "emprestimos")
public class Emprestimo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "valor", nullable = false)
    private BigDecimal valor;

    @Column(name = "taxa_juros", nullable = false)
    private BigDecimal taxaJuros;

    @Column(name = "prazo_meses", nullable = false)
    private Integer prazoMeses;

    @Column(name = "numero_parcelas", nullable = false)
    private Integer numeroParcelas;

    @Column(name = "parcela", nullable = false)
    private BigDecimal parcela;

    @Column(name = "data_emprestimo", nullable = false)
    private LocalDate dataEmprestimo;

    @Column(name = "data_termino", nullable = false)
    private LocalDate dataTermino;

    @Column(name = "valor_total")
    private BigDecimal valorTotal;

    public Emprestimo(LocalDate dataInicial, LocalDate dataFinal) {
    }
    /**
     * Construtor padrão sem argumentos.
     * <p>
     * Necessário para frameworks como Hibernate que requerem um construtor padrão para instanciar a entidade.
     * </p>
     */
    public Emprestimo() {
        // Construtor padrão sem argumentos
    }

    @PrePersist
    @PreUpdate
    public void validate() {
        if (prazoMeses == null || prazoMeses <= 0) {
            throw new IllegalArgumentException("O prazo em meses deve ser definido e maior que zero antes de salvar o empréstimo.");
        }
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
}