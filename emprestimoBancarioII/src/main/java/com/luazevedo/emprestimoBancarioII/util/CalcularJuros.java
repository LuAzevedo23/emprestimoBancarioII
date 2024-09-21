package com.luazevedo.emprestimoBancarioII.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Classe utilitária para cálculos de juros.
 */
public class CalcularJuros {
    public static double calcularMontanteJurosSimples(double principal, double taxaJuros, double tempo){
        double taxaDecimal = taxaJuros / 100;
        return principal + (principal * taxaDecimal * tempo);

    }

    public static BigDecimal calcularMontanteJurosCompostos(BigDecimal capital, BigDecimal taxaJuros, int prazoMeses) {
        if (capital.compareTo(BigDecimal.ZERO) <= 0 || taxaJuros.compareTo(BigDecimal.ZERO) <= 0 || prazoMeses <= 0) {
            throw new IllegalArgumentException("Todos os parâmetros devem ser positivos.");
        }
        BigDecimal taxaMensal = taxaJuros;
        BigDecimal montante = capital.multiply(
                BigDecimal.ONE.add(taxaMensal).pow(prazoMeses)
                );
        return montante.setScale(2, RoundingMode.HALF_UP);

    }
    //Método para calcular juros simples
    public static double calcularJurosSimples(double capital, double taxa, int tempo) {
        return capital * taxa * tempo; // Retorna apenas os juros
    }
    //Método para calcular juros compostos
    public static double calcularJurosCompostos(double capital, double taxa, int tempo){
        return capital * Math.pow((1 + taxa), tempo) - capital; //Aqui retorna apenas os juros
    }
    //Método para calcular o montante total com base no tipo de juros
    public static BigDecimal calcularJuros(BigDecimal valor, BigDecimal taxaJuros, Integer prazoMeses, boolean jurosCompostos){
       if (valor.compareTo(BigDecimal.ZERO)<= 0 || taxaJuros.compareTo(BigDecimal.ZERO)<= 0 || prazoMeses <= 0){
           throw new IllegalArgumentException("Todos os parâmetros devem ser positivos.");
       }
       if(jurosCompostos){
           return calcularMontanteJurosCompostos(valor, taxaJuros, prazoMeses);
       }else {
           double montanteSimples = calcularMontanteJurosSimples(valor.doubleValue(), taxaJuros.doubleValue(), prazoMeses);
           return BigDecimal.valueOf(montanteSimples).setScale(2,RoundingMode.HALF_UP);
       }
    }
}
