package com.luazevedo.emprestimoBancarioII.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalcularJurosTest {

    @Test
    void testCalcularMontanteJurosSimples(){
        //Valores de entrada
        double principal = 1000.0;
        double taxaJuros = 5.0;
        double tempo = 1.0;
        double montanteEsperado = 1050.0;

        double montanteCalculado = CalcularJuros.calcularMontanteJurosSimples(principal,taxaJuros, tempo);

        Assertions.assertEquals(montanteEsperado, montanteCalculado);
    }

    @Test
    public void testCalcularMontanteJurosCompostos() {
        //Valores de entrada
        BigDecimal capital = new BigDecimal("1000");
        BigDecimal taxaJuros = new BigDecimal("0.05");
        int prazoMeses = 12;

        BigDecimal resultadoEsperado = new BigDecimal("1795.86");
        BigDecimal resultadoObtido = CalcularJuros.calcularMontanteJurosCompostos(capital, taxaJuros, prazoMeses);

        assertEquals(resultadoEsperado, resultadoObtido);
    }
}
