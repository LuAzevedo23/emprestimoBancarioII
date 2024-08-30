package com.luazevedo.emprestimoBancarioII.emprestimo.entity;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Classe EmprestimoPessoal
 * <p>
 * Representa um empréstimo pessoal, com lógica específica para calcular o valor total a ser pago.
 * </p>
 * @author Luciene Azevedo
 */
public class EmprestimoPessoal extends Emprestimo {
    /**
     * Percentual adicional para empréstimos pessoais.
     */
    private static final BigDecimal PERCENTUAL_ADICIONAL = BigDecimal.valueOf(0.02);

    /**
     * Calcula o valor total a ser pago em um empréstimo pessoal, incluindo juros e o percentual adicional específico.
     *
     * @return o valor total a ser pago.
     */
    @Override
    public BigDecimal calcularValorTotal() {
        BigDecimal valorBase = getValor();
        BigDecimal juros = valorBase.multiply(getTaxaJuros());
        BigDecimal adicional = valorBase.multiply(PERCENTUAL_ADICIONAL);
        return valorBase.add(juros).add(adicional).setScale(2, RoundingMode.HALF_UP);
    }
}
