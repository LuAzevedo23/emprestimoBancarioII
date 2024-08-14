package com.luazevedo.emprestimoBancarioII.service;

import com.luazevedo.emprestimoBancarioII.dto.HistoricoEmprestimoDTO;
import com.luazevedo.emprestimoBancarioII.entity.HistoricoEmprestimo;
import com.luazevedo.emprestimoBancarioII.mapper.HistoricoEmprestimoMapper;
import com.luazevedo.emprestimoBancarioII.repository.HistoricoEmprestimoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Serviço para manipulação das entidades {@link HistoricoEmprestimo}.
 */
@Service
public class HistoricoEmprestimoService {

    private final HistoricoEmprestimoRepository repository;
    private final HistoricoEmprestimoMapper mapper;

    @Autowired
    public HistoricoEmprestimoService(HistoricoEmprestimoRepository repository, HistoricoEmprestimoMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    /**
     * Encontra todos os {@link HistoricoEmprestimo} e os converte para DTOs.
     *
     * @return uma lista de {@link HistoricoEmprestimoDTO}
     */
    public List<HistoricoEmprestimoDTO> findAll() {
        List<HistoricoEmprestimo> historicos = repository.findAll();
        return mapper.paraDTO(historicos);
    }

    /**
     * Encontra um {@link HistoricoEmprestimo} pelo ID e o converte para DTO.
     *
     * @param id o ID do HistoricoEmprestimo
     * @return o {@link HistoricoEmprestimoDTO} correspondente
     * @throws RuntimeException se o HistoricoEmprestimo não for encontrado
     */
    public HistoricoEmprestimoDTO findById(Long id) {
        HistoricoEmprestimo historico = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("HistoricoEmprestimo com id " + id + " não foi encontrado"));
        return mapper.paraDTO(historico);
    }

    /**
     * Salva um novo {@link HistoricoEmprestimo} a partir de um DTO.
     *
     * @param historicoEmprestimoDTO o DTO do HistoricoEmprestimo a ser salvo
     * @return o ID do HistoricoEmprestimo salvo
     */
    public Long save(HistoricoEmprestimoDTO historicoEmprestimoDTO) {
        HistoricoEmprestimo historico = mapper.paraEntity(historicoEmprestimoDTO);
        return repository.save(historico).getId();
    }

    /**
     * Deleta um {@link HistoricoEmprestimo} pelo ID.
     *
     * @param id o ID do HistoricoEmprestimo
     * @throws RuntimeException se o HistoricoEmprestimo não for encontrado
     */
    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new RuntimeException("HistoricoEmprestimo com id " + id + " não foi encontrado");
        }
    }

    /**
     * Atualiza um {@link HistoricoEmprestimo} existente a partir de um DTO.
     *
     * @param historicoEmprestimoDTO o DTO do HistoricoEmprestimo a ser atualizado
     * @return o ID do HistoricoEmprestimo atualizado
     * @throws RuntimeException se o HistoricoEmprestimo não for encontrado
     */
    public Long update(HistoricoEmprestimoDTO historicoEmprestimoDTO) {
        HistoricoEmprestimo historico = mapper.paraEntity(historicoEmprestimoDTO);
        if (repository.existsById(historico.getId())) {
            return repository.save(historico).getId();
        } else {
            throw new RuntimeException("HistoricoEmprestimo com id " + historico.getId() + " não foi encontrado");
        }
    }
}
