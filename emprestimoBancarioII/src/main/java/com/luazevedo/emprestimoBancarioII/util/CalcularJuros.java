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
     * @param principal  O valor principal do empréstimo.
     * @param taxaJuros  A taxa de juros (em decimal, por exemplo, 0.05 para 5%).
     * @param prazoAnos  O prazo do empréstimo em anos.
     * @return O montante total a ser pago.
     */

    public static BigDecimal calcularMontanteJurosSimples(BigDecimal principal, BigDecimal taxaJuros, int prazoAnos) {
        BigDecimal juros = principal.multiply(taxaJuros).multiply(BigDecimal.valueOf(prazoAnos));
        return principal.add(juros).setScale(2, RoundingMode.HALF_UP);
    }

    /**
     * Calcula o montante total de um empréstimo com juros compostos.
     *
     * @param principal  O valor principal do empréstimo.
     * @param taxaJuros  A taxa de juros (em decimal, por exemplo, 0.05 para 5%).
     * @param prazoAnos  O prazo do empréstimo em anos.
     * @return O montante total a ser pago.
     */
    public static BigDecimal calcularMontanteJurosCompostos(BigDecimal principal, BigDecimal taxaJuros, int prazoAnos) {
        BigDecimal montante = principal.multiply(
                BigDecimal.ONE.add(taxaJuros).pow(prazoAnos)
        );
        return montante.setScale(2, RoundingMode.HALF_UP);
    }
}
