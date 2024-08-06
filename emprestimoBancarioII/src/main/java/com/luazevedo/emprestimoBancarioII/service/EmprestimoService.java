package com.luazevedo.emprestimoBancarioII.service;

import com.luazevedo.emprestimoBancarioII.entity.Emprestimo;
import com.luazevedo.emprestimoBancarioII.exception.ValorNaoExistenteNaBaseDeDadosException;
import com.luazevedo.emprestimoBancarioII.repository.EmprestimoRepository;
import org.springframework.stereotype.Service;



@Service
public class EmprestimoService {

    private final EmprestimoRepository emprestimoRepository;

    public EmprestimoService(EmprestimoRepository emprestimoRepository) {
        this.emprestimoRepository = emprestimoRepository;
    }

    public Emprestimo save(Emprestimo emprestimo) {
        return emprestimoRepository.save(emprestimo);
    }

    public Emprestimo findById(Long id) {
        return emprestimoRepository.findById(id)
                .orElseThrow(() -> new ValorNaoExistenteNaBaseDeDadosException("Empréstimo com ID " + id + " não encontrado"));
    }

    public void delete(Long id) {
        if (!emprestimoRepository.existsById(id)) {
            throw new ValorNaoExistenteNaBaseDeDadosException("Empréstimo com ID " + id + " não encontrado");
        }
        emprestimoRepository.deleteById(id);
    }

    public Long update(Emprestimo emprestimo) {
        if (!emprestimoRepository.existsById(emprestimo.getId())) {
            throw new ValorNaoExistenteNaBaseDeDadosException("Empréstimo com ID " + emprestimo.getId() + " não encontrado");
        }
        Emprestimo updatedEmprestimo = emprestimoRepository.save(emprestimo);
        return updatedEmprestimo.getId();
    }
}