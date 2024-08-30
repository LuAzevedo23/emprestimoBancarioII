package com.luazevedo.emprestimoBancarioII.emprestimo.factory;

import com.luazevedo.emprestimoBancarioII.emprestimo.entity.EmprestimoPessoal;

/**
 * Classe EmprestimoPessoalFactory
 * <p>
 * Implementa a criação de um Empréstimo Pessoal.
 * </p>
 * @author Luciene Azevedo
 */
public class EmprestimoPessoalFactory extends EmprestimoFactory {

    /**
     * Cria uma instância de Empréstimo Pessoal.
     *
     * @return uma instância de {@link EmprestimoPessoal}.
     */
    public EmprestimoPessoal criarEmprestimo() {
        // Aqui você pode adicionar lógica específica para criar um Empréstimo Pessoal
        return new EmprestimoPessoal();
    }
}
