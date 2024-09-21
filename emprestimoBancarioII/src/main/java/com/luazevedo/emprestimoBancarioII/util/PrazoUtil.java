package com.luazevedo.emprestimoBancarioII.util;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * Classe utilitária para cálculos de prazo entre datas.
 */
public class PrazoUtil {

    /**
     * Calcula a diferença em dias entre duas datas.
     *
     * @param dataInicial A data inicial.
     * @param dataFinal   A data final.
     * @return A diferença em dias entre as duas datas.
     */

    //ChronoUnit.DAYS.between: Calcula a diferença em dias.
    //dataInicial.isAfter(dataFinal): Verifica se a data inicial é posterior à data final.
    // Se for, retorna a diferença negativa.

    public long calcularDiferencaEmDias(LocalDate dataInicial, LocalDate dataFinal) {
        return dataInicial.isAfter(dataFinal) ?
                -ChronoUnit.DAYS.between(dataFinal, dataInicial) :
                ChronoUnit.DAYS.between(dataInicial, dataFinal);
    }
}

