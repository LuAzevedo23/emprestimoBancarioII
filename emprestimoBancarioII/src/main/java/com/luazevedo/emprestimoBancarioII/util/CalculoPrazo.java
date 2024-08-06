package com.luazevedo.emprestimoBancarioII.util;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * Classe utilitária para cálculos de prazo.
 */
public class CalculoPrazo {

    /**
     * Calcula o prazo de um empréstimo em dias.
     *
     * @param dataInicial  A data inicial do empréstimo.
     * @param dataFinal    A data final do empréstimo.
     * @return O prazo do empréstimo em dias.
     */
    public static long calcularPrazoEmDias(LocalDate dataInicial, LocalDate dataFinal) {
        return ChronoUnit.DAYS.between(dataInicial, dataFinal);
    }

    /**
     * Calcula o prazo de um empréstimo em meses.
     *
     * @param dataInicial  A data inicial do empréstimo.
     * @param dataFinal    A data final do empréstimo.
     * @return O prazo do empréstimo em meses.
     */
    public static long calcularPrazoEmMeses(LocalDate dataInicial, LocalDate dataFinal) {
        return ChronoUnit.MONTHS.between(dataInicial, dataFinal);
    }

    /**
     * Calcula o prazo de um empréstimo em anos.
     *
     * @param dataInicial  A data inicial do empréstimo.
     * @param dataFinal    A data final do empréstimo.
     * @return O prazo do empréstimo em anos.
     */
    public static long calcularPrazoEmAnos(LocalDate dataInicial, LocalDate dataFinal) {
        return ChronoUnit.YEARS.between(dataInicial, dataFinal);
    }
}
