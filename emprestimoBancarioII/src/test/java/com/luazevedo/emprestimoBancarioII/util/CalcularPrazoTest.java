package com.luazevedo.emprestimoBancarioII.util;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalcularPrazoTest {

    //Teste para verificar se o cálculo do prazo em dias está correto.
    //Considera 10 dias de diferença entre 1º de Janeiro e 10 de Janeiro(excluindo o dia inicial)
    @Test
    public void testCalcularPrazoEmDias(){
        LocalDate dataInicial = LocalDate.of(2024,1,1);
        LocalDate dataFinal = LocalDate.of(2024,1,10);

        long resultado = CalculoPrazo.calcularPrazoEmDias(dataInicial, dataFinal);

        assertEquals(9, resultado); // 10 dias - 1 dia (início)
    }

    //Teste o cálculo do prazo em meses, considerando 3 meses entre 1º de Janeiro e 1º de Abril
    @Test
    public void testCalcularPrazoEmMeses(){
        LocalDate dataInicial = LocalDate.of(2024,1,1);
        LocalDate dataFinal = LocalDate.of(2024,4,1);

        long resultado = CalculoPrazo.calcularPrazoEmMeses(dataInicial, dataFinal);

        assertEquals(3, resultado); // 3 meses
    }
    //Verifica o cálculo do prazo em anos, onde há uma diferença de 4 anos entre 1º de Janeiro de 2020 e
    //1º de Janeiro de 2024.
    @Test
    public void testCalcularPrazoEmAnos(){
       LocalDate dataInicial = LocalDate.of(2020,1,1) ;
       LocalDate dataFinal = LocalDate.of(2024,1,1);

       long resultado = CalculoPrazo.calcularPrazoEmAnos(dataInicial, dataFinal);

       assertEquals(4, resultado); //4 anos
    }
}
