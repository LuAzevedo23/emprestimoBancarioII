package com.luazevedo.emprestimoBancarioII.service;

import com.luazevedo.emprestimoBancarioII.dto.EmprestimoDTO;
import com.luazevedo.emprestimoBancarioII.entity.Emprestimo;
import com.luazevedo.emprestimoBancarioII.mapper.EmprestimoMapper;
import com.luazevedo.emprestimoBancarioII.repository.EmprestimoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmprestimoService {

    @Autowired
    private EmprestimoRepository repository;
    @Autowired
    private EmprestimoMapper mapper;

    public List<EmprestimoDTO> findAll() {
        List<Emprestimo> emprestimos = repository.findAll();
        return mapper.paraDTO(emprestimos);
    }

    public EmprestimoDTO findById(Long id) {
        Emprestimo emprestimo = repository.findById(id).orElseThrow(
                () -> new RuntimeException("Emprestimo com id" + id + "Não foi encontrado"));
        return mapper.paraDTO(emprestimo);

    }

    public Long save(EmprestimoDTO emprestimoDTO) {
        Emprestimo emprestimo = mapper.paraEntity(emprestimoDTO);
        return repository.save(emprestimo).getId();
    }

    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new RuntimeException("Emprestimo com id" + id + "Não foi encontrado");
        }
    }
    public Long update (EmprestimoDTO emprestimoDTO) {
        Emprestimo emprestimo = mapper.paraEntity(emprestimoDTO);
        if (repository.existsById(emprestimo.getId())) {
            return repository.save(emprestimo).getId();
        } else {
            throw new RuntimeException("Emprestimo com id" + emprestimo.getId() + "Não foi encontrado");
        }
    }
}
