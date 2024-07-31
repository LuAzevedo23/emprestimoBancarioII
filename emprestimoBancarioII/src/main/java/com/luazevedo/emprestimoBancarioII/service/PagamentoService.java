package com.luazevedo.emprestimoBancarioII.service;

import com.luazevedo.emprestimoBancarioII.dto.PagamentoDTO;
import com.luazevedo.emprestimoBancarioII.entity.Pagamento;
import com.luazevedo.emprestimoBancarioII.mapper.PagamentoMapper;
import com.luazevedo.emprestimoBancarioII.repository.PagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PagamentoService {

    @Autowired
    private PagamentoRepository repository;
    @Autowired
    private PagamentoMapper mapper;

    public List<PagamentoDTO> findAll() {
        List<Pagamento> pagamentos = repository.findAll();
        return mapper.paraDTO(pagamentos);
    }

    public PagamentoDTO findById(Long id) {
        Pagamento pagamento = repository.findById(id).orElseThrow(
                () -> new RuntimeException("Pagamento com id" + id + "Não foi encontrado"));
        return mapper.paraDTO(pagamento);

    }

    public Long save(PagamentoDTO pagamentoDTO) {
        Pagamento pagamentoService = (Pagamento) mapper.paraEntity(pagamentoDTO);
        return repository.save(pagamentoService).getId();
    }

    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new RuntimeException("Pagamento com id" + id + "Não foi encontrado");
        }
    }

    public Long update(PagamentoDTO pagamentoDTO) {
        Pagamento pagamentoService = (Pagamento) mapper.paraEntity(pagamentoDTO);
        if (repository.existsById(pagamentoService.getId())) {
            return repository.save(pagamentoService).getId();
        } else {
            throw new RuntimeException("Pagamento com id" + pagamentoService.getId() + "Não foi encontrado");
        }
    }
}
