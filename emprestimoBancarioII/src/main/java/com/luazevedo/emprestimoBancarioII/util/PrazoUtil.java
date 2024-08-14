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
     * @param dataFinal A data final.
     * @return A diferença em dias entre as duas datas.
     */
    public long calcularDiferencaEmDias(LocalDate dataInicial, LocalDate dataFinal) {
        return ChronoUnit.DAYS.between(dataInicial, dataFinal);
    }
}

