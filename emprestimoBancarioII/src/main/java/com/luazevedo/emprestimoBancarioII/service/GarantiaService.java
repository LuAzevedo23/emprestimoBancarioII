package com.luazevedo.emprestimoBancarioII.service;

import com.luazevedo.emprestimoBancarioII.dto.GarantiaDTO;
import com.luazevedo.emprestimoBancarioII.entity.Garantia;
import com.luazevedo.emprestimoBancarioII.mapper.GarantiaMapper;
import com.luazevedo.emprestimoBancarioII.repository.GarantiaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Serviço para manipulação das entidades {@link Garantia}.
 */

@Service
public class GarantiaService {

    private final GarantiaRepository repository;
    private final GarantiaMapper mapper;

    @Autowired
    public GarantiaService(GarantiaRepository repository, GarantiaMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    /**
     * Encontra todos os {@link Garantia} e os converte para DTOs.
     *
     * @return uma lista de {@link GarantiaDTO}
     */
    public List<GarantiaDTO> findAll() {
        List<Garantia> garantias = repository.findAll();
        return mapper.paraDTO(garantias);
    }

    /**
     * Encontra uma {@link Garantia} pelo ID e a converte para DTO.
     *
     * @param id o ID da Garantia
     * @return o {@link GarantiaDTO} correspondente
     * @throws RuntimeException se a Garantia não for encontrada
     */
    public GarantiaDTO findById(Long id) {
        Garantia garantia = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Garantia com id " + id + " não foi encontrada"));
        return mapper.paraDTO(garantia);
    }

    /**
     * Salva uma nova {@link Garantia} a partir de um DTO.
     *
     * @param garantiaDTO o DTO da Garantia a ser salva
     * @return o ID da Garantia salva
     */
    public Long save(GarantiaDTO garantiaDTO) {
        Garantia garantia = mapper.paraEntity(garantiaDTO);
        return repository.save(garantia).getId();
    }

    /**
     * Deleta uma {@link Garantia} pelo ID.
     *
     * @param id o ID da Garantia
     * @throws RuntimeException se a Garantia não for encontrada
     */
    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new RuntimeException("Garantia com id " + id + " não foi encontrada");
        }
    }

    /**
     * Atualiza uma {@link Garantia} existente a partir de um DTO.
     *
     * @param garantiaDTO o DTO da Garantia a ser atualizada
     * @return o ID da Garantia atualizada
     * @throws RuntimeException se a Garantia não for encontrada
     */
    public Long update(GarantiaDTO garantiaDTO) {
        Garantia garantia = mapper.paraEntity(garantiaDTO);
        if (repository.existsById(garantia.getId())) {
            return repository.save(garantia).getId();
        } else {
            throw new RuntimeException("Garantia com id " + garantia.getId() + " não foi encontrada");
        }
    }
}
