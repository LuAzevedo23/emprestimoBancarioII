package com.luazevedo.emprestimoBancarioII.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Classe utilitária para cálculos de juros.
 */
public class CalcularJuros {

    /**
     * Calcula o montante total de um empréstimo com juros simples.
     *
     * @param valor  O valor  do empréstimo.
     * @param taxaJuros  A taxa de juros (em decimal, por exemplo, 0.05 para 5%).
     * @param prazoAnos  O prazo do empréstimo em anos.
     * @return O montante total a ser pago.
     */
    public static BigDecimal calcularMontanteJurosSimples(BigDecimal valor, BigDecimal taxaJuros, int prazoAnos) {
        BigDecimal juros = valor.multiply(taxaJuros).multiply(BigDecimal.valueOf(prazoAnos));
        return valor.add(juros).setScale(2, RoundingMode.HALF_UP);
    }

    /**
     * Calcula o montante total de um empréstimo com juros compostos.
     *
     * @param valor  O valor do empréstimo.
     * @param taxaJuros  A taxa de juros (em decimal, por exemplo, 0.05 para 5%).
     * @param prazoMeses  O prazo do empréstimo em meses.
     * @return O montante total a ser pago.
     */
    public static BigDecimal calcularMontanteJurosCompostos(BigDecimal valor, BigDecimal taxaJuros, int prazoMeses) {
        BigDecimal taxaMensal = taxaJuros.divide(BigDecimal.valueOf(12), RoundingMode.HALF_UP);
        BigDecimal montante = valor.multiply(
                BigDecimal.ONE.add(taxaMensal).pow(prazoMeses)
        );
        return montante.setScale(2, RoundingMode.HALF_UP);
    }
}
