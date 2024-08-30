package com.luazevedo.emprestimoBancarioII.emprestimo;

import com.luazevedo.emprestimoBancarioII.entity.Emprestimo;

/**
 * Interface EmprestimoFactory
 * <p>
 * Define o contrato para a criação de diferentes tipos de empréstimos.
 * </p>
 * @author Luciene Azevedo
 */
public interface EmprestimoFactory {

    /**
 * Cria uma instância de Empréstimo.
 *
 * @return uma instância de {@link Emprestimo}.
 */

Emprestimo criarEmprestimo();
}



