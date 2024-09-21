package com.luazevedo.emprestimoBancarioII.entity;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "Entidade garantia representa a garantia do cliente no sistema.")
public class Garantia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "O ID único da garantia", example = "1", required = true)
    private Long id;

    @Column(nullable = false)
    @Schema(description = "Descrição da garantia.")
    private String descricao;

    @Column(nullable = false)
    @Schema(description = "Valor da garantia.")
    private BigDecimal valor;

    @ManyToOne
    @JoinColumn(name = "emprestimo_id")
    @Schema(description = "Empréstimo associado à garantia.")
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




