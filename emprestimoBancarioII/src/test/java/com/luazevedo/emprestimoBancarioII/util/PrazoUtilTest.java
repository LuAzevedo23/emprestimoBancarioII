package com.luazevedo.emprestimoBancarioII.util;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PrazoUtilTest {

    //Verifica a diferença entre duas datas válidas, onde dataInicial é anterior a dataFinal.

    @Test
    void testCalcularDiferencaEmDias() {
        LocalDate dataInicial = LocalDate.of(2023, 1, 1);
        LocalDate dataFinal = LocalDate.of(2023, 1, 10);

        PrazoUtil prazoUtil = new PrazoUtil();
        long resultado = prazoUtil.calcularDiferencaEmDias(dataInicial, dataFinal);
        long esperado = 9; // Diferença de 9 dias

        assertEquals(esperado, resultado);
    }

    // Verifica o comportamento quando as duas datas são iguais, o que deve resultar em 0 dias de diferença.
    @Test
    void testCalcularDiferencaEmDiasComDatasIguais() {
        LocalDate data = LocalDate.of(2023, 1, 1);

        PrazoUtil prazoUtil = new PrazoUtil();
        long resultado = prazoUtil.calcularDiferencaEmDias(data, data);
        long esperado = 0; // Diferença de 0 dias qundo as datas são iguais

        assertEquals(esperado, resultado);
    }

    //Testa a situação em que a data inicial é posterior à data final, resultando em um valor negativo.(ordem invertida).
    @Test
    void testCalcularDiferencaEmDiasComDatasInvertidas() {
        LocalDate dataInicial = LocalDate.of(2023, 1, 10);
        LocalDate dataFinal = LocalDate.of(2023, 1, 1);

        PrazoUtil prazoUtil = new PrazoUtil();
        long resultado = prazoUtil.calcularDiferencaEmDias(dataInicial, dataFinal);
        long esperado = -9; // Diferença negativa de -9 dias.

        assertEquals(esperado, resultado);
    }
}