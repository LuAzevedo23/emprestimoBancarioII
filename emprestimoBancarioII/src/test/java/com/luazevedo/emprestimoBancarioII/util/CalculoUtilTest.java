package com.luazevedo.emprestimoBancarioII.util;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CalculoUtilTest {

    //Testa o cálculo dos juros com um valor principal, taxa de juros e prazo em meses.
    @Test
    void testCalcularJuros(){
        BigDecimal principal = new BigDecimal("1000.00");
        BigDecimal taxaJuros = new BigDecimal("5.0");
        int tempo = 6;

        BigDecimal esperado = new BigDecimal("1300.00");
        BigDecimal resultado = CalculoUtil.calcularJuros(principal, taxaJuros, tempo);

        assertEquals(esperado.setScale(2), resultado.setScale(2));
    }

    //Testa o cálculo da parcela mensal, utilizando um valor principal, taxa de juros e prazo.
    @Test
    void testCalcularParcela(){
        BigDecimal valor = new BigDecimal("1000");
        BigDecimal taxaJuros = new BigDecimal("5"); //5%
        Integer prazoMeses = 12; //1 ano

        BigDecimal resultado = CalculoUtil.calcularParcela(valor, taxaJuros, prazoMeses);
        BigDecimal esperado = new BigDecimal("85.61");

        assertEquals(esperado.setScale(2), resultado.setScale(2));
    }
//Verifica se a função retorna corretamente o número de meses entre duas datas.
    @Test
    void testCalcularPrazoEntreDatas(){
        LocalDate dataInicio = LocalDate.of(2023,1,1);
        LocalDate dataFim = LocalDate.of(2023,6,1);

        Integer resultado = CalculoUtil.calcularPrazoEntreDatas(dataInicio, dataFim);
        Integer esperado = 5; // 5 meses

        assertEquals(esperado, resultado);
    }
//Assegura que a função lança uma exceção quando os parâmetros são inválidos.
    @Test
    void testCalcularParcelaComParametrosInvalidos(){
        assertThrows(IllegalArgumentException.class, ()->{
            CalculoUtil.calcularParcela(null, new BigDecimal("5"),12);
        });

        assertThrows(IllegalArgumentException.class, ()-> {
           CalculoUtil.calcularParcela(new BigDecimal("1000"), null, 12);
        });

        assertThrows(IllegalArgumentException.class, ()->{
           CalculoUtil.calcularParcela(new BigDecimal("1000"), new BigDecimal("5"), null);
        });

        assertThrows(IllegalArgumentException.class, ()->{
            CalculoUtil.calcularParcela(new BigDecimal("1000"), new BigDecimal("5"), 0);
        });
    }
}
