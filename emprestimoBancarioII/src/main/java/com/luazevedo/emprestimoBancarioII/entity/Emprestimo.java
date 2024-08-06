package com.luazevedo.emprestimoBancarioII.entity;

import com.luazevedo.emprestimoBancarioII.entity.enums.StatusEmprestimo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Entidade que representa um empréstimo.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "emprestimo")
public class Emprestimo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    private BigDecimal valor;

    private LocalDate dataEmprestimo;

    private LocalDate dataTermino;

    @Enumerated(EnumType.STRING)
    private StatusEmprestimo status;

    private BigDecimal montante; // Adicionado o campo montante

    private BigDecimal taxaJuros;

    /**
     * Obtém a data de solicitação do empréstimo.
     *
     * @return A data de solicitação.
     */
    public LocalDate getDataSolicitacao() {
        return dataEmprestimo;
    }

    /**
     * Obtém a data de aprovação do empréstimo.
     *
     * @return A data de aprovação.
     */
    public LocalDate getDataAprovacao() {
        return dataTermino;
    }

    /**
     * Obtém a taxa de juros aplicada ao empréstimo.
     *
     * @return A taxa de juros.
     */
    public BigDecimal getTaxaJuros() {
        return taxaJuros;
    }

    public void setTaxaJuros(BigDecimal taxaJuros) {
        this.taxaJuros = taxaJuros;
    }

    /**
     * Obtém o prazo do empréstimo em dias.
     *
     * @return O prazo em dias.
     */
    public Integer getPrazo() {
        if (dataEmprestimo != null && dataTermino != null) {
            return (int) java.time.temporal.ChronoUnit.DAYS.between(dataEmprestimo, dataTermino);
        }
        return null;
    }
}