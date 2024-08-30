package com.luazevedo.emprestimoBancarioII.emprestimo.entity;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Classe abstrata Emprestimo
 * <p>
 * Representa um empréstimo genérico, contendo atributos e métodos comuns a todos os tipos de empréstimos.
 * </p>
 * @author Luciene Azevedo
 */
@Setter
@Getter
public abstract class Emprestimo {

    /**
     * Valor total do empréstimo.
     */
    private BigDecimal valor;

    /**
     * Data de início do empréstimo.
     */
    private LocalDate dataInicio;

    /**
     * Data de término do empréstimo.
     */
    private LocalDate dataTermino;

    /**
     * Taxa de juros aplicada ao empréstimo.
     */
    private BigDecimal taxaJuros;

    /**
     * Calcula o valor total a ser pago, incluindo juros.
     *
     * @return o valor total a ser pago.
     */
    public abstract BigDecimal calcularValorTotal();


}
