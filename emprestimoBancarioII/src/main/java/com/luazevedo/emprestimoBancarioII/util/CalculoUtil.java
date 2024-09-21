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


    public static BigDecimal calcularJuros(BigDecimal principal, BigDecimal taxaJuros, int meses) {
        if (principal == null || taxaJuros == null || meses <= 0) {
            throw new IllegalArgumentException("Parâmetros inválidos para cálculo de juros");
        }
        BigDecimal taxaMensal = taxaJuros.divide(BigDecimal.valueOf(100));
        BigDecimal juros = principal.multiply(taxaMensal).multiply(BigDecimal.valueOf(meses));
        return principal.add(juros);
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