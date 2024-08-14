package com.luazevedo.emprestimoBancarioII.util;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Period;

/**
 * Classe utilitária para cálculos financeiros.
 */
public class CalculoUtil {

    /**
     * Calcula o valor total de um empréstimo considerando o valor principal, a taxa de juros e o prazo em meses.
     *
     * @param valor O valor  do empréstimo.
     * @param taxaJuros A taxa de juros aplicada ao empréstimo.
     * @param prazoMeses O prazo do empréstimo em meses.
     * @return O valor total do empréstimo após a aplicação dos juros.
     */
    public static BigDecimal calcularJuros(BigDecimal valor, BigDecimal taxaJuros, Integer prazoMeses) {
        // Utiliza MathContext para definir a precisão do cálculo
        MathContext mathContext = new MathContext(10);

        // Fórmula básica para cálculo de juros simples (exemplo)
        BigDecimal taxaMensal = taxaJuros.divide(BigDecimal.valueOf(100), mathContext);
        BigDecimal fatorJuros = BigDecimal.ONE.add(taxaMensal).pow(prazoMeses);
        return valor.multiply(fatorJuros, mathContext);
    }

    /**
     * Calcula o valor da parcela mensal de um empréstimo.
     *
     * @param valor O valor principal do empréstimo.
     * @param taxaJuros A taxa de juros anual aplicada ao empréstimo.
     * @param prazoMeses O prazo do empréstimo em meses.
     * @return O valor da parcela mensal do empréstimo.
     */
    public static BigDecimal calcularParcela(BigDecimal valor, BigDecimal taxaJuros, Integer prazoMeses) {
        // Verifica se os parâmetros são válidos
        if (valor == null || taxaJuros == null || prazoMeses == null || prazoMeses <= 0) {
            throw new IllegalArgumentException("Os parâmetros não podem ser nulos e o prazo deve ser maior que zero.");
        }

        // Calcula a taxa de juros mensal
        BigDecimal taxaMensal = taxaJuros.divide(BigDecimal.valueOf(100), MathContext.DECIMAL128)
                .divide(BigDecimal.valueOf(12), MathContext.DECIMAL128);

        // Verifica se a taxa de juros mensal é zero para evitar divisão por zero
        if (taxaMensal.compareTo(BigDecimal.ZERO) == 0) {
            return valor.divide(BigDecimal.valueOf(prazoMeses), MathContext.DECIMAL128);
        }

        // Calcula o valor da parcela usando a fórmula de prestação fixa
        BigDecimal fator = BigDecimal.ONE.add(taxaMensal).pow(prazoMeses);
        BigDecimal parcela = valor.multiply(taxaMensal).multiply(fator)
                .divide(fator.subtract(BigDecimal.ONE), MathContext.DECIMAL128)
                .setScale(2, RoundingMode.HALF_UP);

        return parcela;
    }

    public static Integer calcularPrazoEntreDatas(LocalDate dataInicio, LocalDate dataFim) {
        // Lógica para calcular o número de meses entre as duas datas
        return Period.between(dataInicio, dataFim).getMonths();
    }
}