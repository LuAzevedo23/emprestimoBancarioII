package com.luazevedo.emprestimoBancarioII.service;

import com.luazevedo.emprestimoBancarioII.dto.HistoricoEmprestimoDTO;
import com.luazevedo.emprestimoBancarioII.entity.HistoricoEmprestimo;
import com.luazevedo.emprestimoBancarioII.mapper.HistoricoEmprestimoMapper;
import com.luazevedo.emprestimoBancarioII.repository.HistoricoEmprestimoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoricoEmprestimoService {

    @Autowired
    private HistoricoEmprestimoRepository repository;
    @Autowired
    private HistoricoEmprestimoMapper mapper;

    public List<HistoricoEmprestimoDTO> findAll() {
        List<HistoricoEmprestimo> historicoEmprestimos = repository.findAll();
        return mapper.paraDTO(historicoEmprestimos);
    }

    public HistoricoEmprestimoDTO findById(Long id) {
        HistoricoEmprestimo historicoEmprestimo = repository.findById(id).orElseThrow(
                () -> new RuntimeException("HistoricoEmprestimo com id" + id + "Não foi encontrado"));
        return mapper.paraDTO(historicoEmprestimo);

    }

    public Long save(HistoricoEmprestimoDTO historicoEmprestimoDTO) {
        HistoricoEmprestimo historicoEmprestimoService = mapper.paraEntity(historicoEmprestimoDTO);
        return repository.save(historicoEmprestimoService).getId();
    }

    public void delete(Long id) {

        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new RuntimeException("HistoricoEmprestimo com id" + id + "Não foi encontrado");
        }
    }
    public Long update (HistoricoEmprestimoDTO historicoEmprestimoDTO) {
        HistoricoEmprestimo historicoEmprestimoService = mapper.paraEntity(historicoEmprestimoDTO);
        if (repository.existsById(historicoEmprestimoService.getId())) {
            return repository.save(historicoEmprestimoService).getId();
        } else {
            throw new RuntimeException("HistoricoEmprestimo com id" + historicoEmprestimoService.getId() + "Não foi encontrado");
        }
    }

}
