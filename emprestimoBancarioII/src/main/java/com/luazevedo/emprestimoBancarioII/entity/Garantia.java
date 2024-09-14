package com.luazevedo.emprestimoBancarioII.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * Entidade que representa uma garantia associada a um empréstimo.
 * Uma garantia pode ser utilizada para assegurar o cumprimento de um empréstimo.
 *
 * <p>
 * A descrição e o valor da garantia são obrigatórios.
 * </p>
 *
 * @author Luciene Azevedo
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "garantia")
public class Garantia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false)
    private BigDecimal valor;

    @ManyToOne
    @JoinColumn(name = "emprestimo_id")
    private Emprestimo emprestimo;

    /**
     * construtor para criar uma garantia associada a um empréstimo.
     *
     * @param descricao     descricao da garantia
     * @param valor         valor da garantia
     * @param emprestimo    emprestimo associado
     */

    public Garantia (String descricao, BigDecimal valor, Emprestimo emprestimo) {
        this.descricao = descricao;
        this.valor = valor;
        this.emprestimo = emprestimo;
    }
}




