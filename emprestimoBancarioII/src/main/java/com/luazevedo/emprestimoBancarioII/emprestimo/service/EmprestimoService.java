package com.luazevedo.emprestimoBancarioII.emprestimo.service;

import com.luazevedo.emprestimoBancarioII.emprestimo.EmprestimoFactory;
import com.luazevedo.emprestimoBancarioII.emprestimo.factory.EmprestimoEmpresarialFactory;
import com.luazevedo.emprestimoBancarioII.emprestimo.factory.EmprestimoPessoalFactory;
import com.luazevedo.emprestimoBancarioII.entity.Emprestimo;
import org.springframework.stereotype.Service;

/**
 * Classe EmprestimoService
 * <p>
 * Serviço para gerenciar operações relacionadas a empréstimos.
 * </p>
 * @author Luciene Azevedo
 */

@Service("emprestimoEmpresarialService")
public class EmprestimoService {

    /**
     * Cria um novo empréstimo com base no tipo especificado.
     *
     * @param tipo O tipo de empréstimo a ser criado (ex: "PESSOAL", "EMPRESARIAL").
     * @return uma instância de {@link Emprestimo}.
     * @throws IllegalArgumentException se o tipo de empréstimo não for suportado.
     */
    public Emprestimo criarNovoEmprestimo(String tipo) {
        EmprestimoFactory factory;

        // Escolhe a fábrica correta com base no tipo de empréstimo
        if ("PESSOAL".equalsIgnoreCase(tipo)) {
            factory = (EmprestimoFactory) new EmprestimoPessoalFactory();
        } else if ("EMPRESARIAL".equalsIgnoreCase(tipo)) {
            factory = (EmprestimoFactory) new EmprestimoEmpresarialFactory();
        } else {
            throw new IllegalArgumentException("Tipo de empréstimo não suportado.");
        }

        // Usa a fábrica para criar o empréstimo
        return factory.criarEmprestimo();
    }
}
