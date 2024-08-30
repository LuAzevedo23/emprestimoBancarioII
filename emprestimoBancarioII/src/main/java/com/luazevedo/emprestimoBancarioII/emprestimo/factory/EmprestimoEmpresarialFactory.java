package com.luazevedo.emprestimoBancarioII.emprestimo.factory;

import com.luazevedo.emprestimoBancarioII.emprestimo.entity.EmprestimoEmpresarial;

/**
 * Classe EmprestimoEmpresarialFactory
 * <p>
 * Implementa a criação de um Empréstimo Empresarial.
 * </p>
 * @author Luciene Azevedo
 */
public class EmprestimoEmpresarialFactory extends EmprestimoFactory {

    /**
     * Cria uma instância de Empréstimo Empresarial.
     *
     * @return uma instância de {@link EmprestimoEmpresarial}.
     */
    public EmprestimoEmpresarial criarEmprestimo() {
        // Aqui você pode adicionar lógica específica para criar um Empréstimo Empresarial
        return new EmprestimoEmpresarial();
    }
}
