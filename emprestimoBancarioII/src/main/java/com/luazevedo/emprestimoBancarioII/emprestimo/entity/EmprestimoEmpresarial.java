package com.luazevedo.emprestimoBancarioII.emprestimo.entity;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Classe EmprestimoEmpresarial
 * <p>
 * Representa um empréstimo empresarial, com lógica específica para calcular o valor total a ser pago.
 * </p>
 * @author Luciene Azevedo
 */
public class EmprestimoEmpresarial extends Emprestimo {

    /**
     * Desconto aplicado a empréstimos empresariais.
     */
    private static final BigDecimal DESCONTO = BigDecimal.valueOf(0.01);

    /**
     * Calcula o valor total a ser pago em um empréstimo empresarial, incluindo juros e aplicando um desconto.
     *
     * @return o valor total a ser pago.
     */
    @Override
    public BigDecimal calcularValorTotal() {
        BigDecimal valorBase = getValor();
        BigDecimal juros = valorBase.multiply(getTaxaJuros());
        BigDecimal desconto = valorBase.multiply(DESCONTO);
        return valorBase.add(juros).subtract(desconto).setScale(2, RoundingMode.HALF_UP);
    }
}
