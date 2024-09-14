package com.luazevedo.emprestimoBancarioII.service;

import com.luazevedo.emprestimoBancarioII.dto.HistoricoEmprestimoDTO;
import com.luazevedo.emprestimoBancarioII.entity.HistoricoEmprestimo;
import com.luazevedo.emprestimoBancarioII.exception.HistoricoEmprestimoNotFoundException;
import com.luazevedo.emprestimoBancarioII.mapper.HistoricoEmprestimoMapper;
import com.luazevedo.emprestimoBancarioII.repository.HistoricoEmprestimoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Serviço para operações relacionadas ao histórico de empréstimos.
 * Contém métodos para gerenciar operações de histórico de empréstimos.
 *
 * @see HistoricoEmprestimo
 * @see HistoricoEmprestimoDTO
 * @see HistoricoEmprestimoMapper
 */
@Service
public class HistoricoEmprestimoService {

    private final HistoricoEmprestimoRepository historicoEmprestimoRepository;
    private final HistoricoEmprestimoMapper historicoEmprestimoMapper;

    @Autowired
    public HistoricoEmprestimoService(HistoricoEmprestimoRepository historicoEmprestimoRepository, HistoricoEmprestimoMapper historicoEmprestimoMapper) {
        this.historicoEmprestimoRepository = historicoEmprestimoRepository;
        this.historicoEmprestimoMapper = historicoEmprestimoMapper;
    }

    /**
     * Salva um novo histórico de empréstimo.
     *
     * @param historicoEmprestimoDTO o DTO do histórico de empréstimo.
     * @return o DTO do histórico de empréstimo salvo.
     */
    public HistoricoEmprestimoDTO save(HistoricoEmprestimoDTO historicoEmprestimoDTO) {
        HistoricoEmprestimo historicoEmprestimo = historicoEmprestimoMapper.paraEntity(historicoEmprestimoDTO);
        HistoricoEmprestimo savedHistorico = historicoEmprestimoRepository.save(historicoEmprestimo);
        return historicoEmprestimoMapper.paraDTO(savedHistorico);
    }

    /**
     * Encontra um historico de empréstimo pelo ID.
     *
     * @param id O ID do histórico de empréstimo pelo ID.
     * @return o DTO do histórico de empréstimo.
     * @throws HistoricoEmprestimoNotFoundException Se o histórico de empréstimo nao for encontrado,
     */
    public HistoricoEmprestimoDTO findById(Long id){
        HistoricoEmprestimo historicoEmprestimo = historicoEmprestimoRepository.findById(id)
                .orElseThrow(() -> new HistoricoEmprestimoNotFoundException("Histórico de Empréstimo não encontrado com ID: " + id));
        return historicoEmprestimoMapper.paraDTO(historicoEmprestimo);
    }

    /**
     * Retorna todos os históricos de empréstimos.
     *
     * @return Uma lista de DTOs de históricos de empréstimos.
     */
    public List<HistoricoEmprestimoDTO> findAll(){
        List<HistoricoEmprestimo> historicos = historicoEmprestimoRepository.findAll();
        return historicos.stream()
                .map(historicoEmprestimoMapper::paraDTO)
                .collect(Collectors.toList());
    }

    /**
     * Deleta um histórico de empréstimo pelo ID.
     *
     * @param id O ID do histórico de empréstimo a ser deletad.
     * @throws HistoricoEmprestimoNotFoundException Se o histórico de empréstimo não for encontrado.
     */
    public void delete(Long id){
        if(!historicoEmprestimoRepository.existsById(id)){
            throw new HistoricoEmprestimoNotFoundException("Histórico de Empréstimo não encontrado com ID: " + id);
        }
        historicoEmprestimoRepository.deleteById(id);
    }

    /**
     * Atualiza um histórico de empréstimo existente a partir de um DTO.
     *
     * @param id O ID do histórico de empréstimo a ser atualizado.
     * @param historicoEmprestimoDTO O DTO com os novos dados.
     * @throws HistoricoEmprestimoNotFoundException Se o histórico de empréstimo não for encontrado.
     */
    public void update(Long id, HistoricoEmprestimoDTO historicoEmprestimoDTO) {
        HistoricoEmprestimo historicoExistente = historicoEmprestimoRepository.findById(id)
                .orElseThrow(() -> new HistoricoEmprestimoNotFoundException("Histórico de Empréstimo não encontrado com ID: " + id));

        // Converte LocalDate para Instant
        Instant dataEventoInstant = historicoEmprestimoDTO.getDataEvento().atStartOfDay().toInstant(java.time.ZoneOffset.UTC);
        historicoExistente.setDataEvento(dataEventoInstant);
        historicoExistente.setDescricao(historicoEmprestimoDTO.getDescricao());

        historicoEmprestimoRepository.save(historicoExistente);
    }
}