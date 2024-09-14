package com.luazevedo.emprestimoBancarioII.service;


import com.luazevedo.emprestimoBancarioII.dto.ConfiguracaoDTO;
import com.luazevedo.emprestimoBancarioII.entity.Configuracao;
import com.luazevedo.emprestimoBancarioII.mapper.ConfiguracaoMapper;
import com.luazevedo.emprestimoBancarioII.repository.ConfiguracaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Serviço para manipulação das entidades {@link Configuracao}.
 * Realiza operações de CRUD e mapeamento de DTOs para a entidade Configuracao.
 *
 * @see ConfiguracaoDTO
 * @see Configuracao
 * @see ConfiguracaoMapper
 * @see ConfiguracaoRepository
 */

@Service
public class ConfiguracaoService {

    private final ConfiguracaoRepository repository;
    private final ConfiguracaoMapper mapper;

    @Autowired
    public ConfiguracaoService(ConfiguracaoRepository repository, ConfiguracaoMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    /**
     * Encontra todos os {@link Configuracao} e os converte para DTOs.
     *
     * @return uma lista de {@link ConfiguracaoDTO}
     */
    public List<ConfiguracaoDTO> findAll() {
        List<Configuracao> configuracoes = repository.findAll();
        return mapper.paraDTO(configuracoes);
    }

    /**
     * Encontra um {@link Configuracao} pelo ID e o converte para DTO.
     *
     * @param id o ID da Configuracao
     * @return o {@link ConfiguracaoDTO} correspondente
     * @throws RuntimeException se a Configuracao não for encontrada
     */
    public ConfiguracaoDTO findById(Long id) {
        Configuracao configuracao = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Configuracao com id " + id + " não foi encontrada"));
        return mapper.paraDTO(configuracao);
    }

    /**
     * Salva uma nova {@link Configuracao} a partir de um DTO.
     *
     * @param configuracaoDTO o DTO da Configuracao a ser salva
     * @return o ID da Configuracao salva
     */
    public Long save(ConfiguracaoDTO configuracaoDTO) {
        Configuracao configuracao = mapper.paraEntity(configuracaoDTO);
        return repository.save(configuracao).getId();
    }

    /**
     * Deleta uma {@link Configuracao} pelo ID.
     *
     * @param id o ID da Configuracao
     * @throws RuntimeException se a Configuracao não for encontrada
     */
    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new RuntimeException("Configuracao com id " + id + " não foi encontrada");
        }
    }

    /**
     * Atualiza uma {@link Configuracao} existente a partir de um DTO.
     *
     * @param configuracaoDTO o DTO da Configuracao a ser atualizada
     * @return o ID da Configuracao atualizada
     * @throws RuntimeException se a Configuracao não for encontrada
     */
    public Long update(ConfiguracaoDTO configuracaoDTO) {
        Configuracao configuracao = mapper.paraEntity(configuracaoDTO);
        if (repository.existsById(configuracao.getId())) {
            return repository.save(configuracao).getId();
        } else {
            throw new RuntimeException("Configuracao com id " + configuracao.getId() + " não foi encontrada");
        }
    }
}