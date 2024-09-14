package com.luazevedo.emprestimoBancarioII.service;

import com.luazevedo.emprestimoBancarioII.dto.GarantiaDTO;
import com.luazevedo.emprestimoBancarioII.entity.Garantia;
import com.luazevedo.emprestimoBancarioII.exception.GarantiaNotFoundException;
import com.luazevedo.emprestimoBancarioII.mapper.GarantiaMapper;
import com.luazevedo.emprestimoBancarioII.repository.GarantiaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Serviço para operações relacionadas a garantias.
 * Contém métodos para gerenciar operações de garantia.
 *
 * @see Garantia
 * @see GarantiaDTO
 * @see GarantiaMapper
 */
@Service
public class GarantiaService {

    private final GarantiaMapper garantiaMapper;
    private final GarantiaRepository garantiaRepository;

    @Autowired
    public GarantiaService(GarantiaRepository garantiaRepository, GarantiaMapper garantiaMapper, GarantiaMapper garantiaMapper1) {
        this.garantiaRepository = garantiaRepository;
        this.garantiaMapper = garantiaMapper1;
    }

    /**
     * Salva uma nova garantia.
     *
     * @param garantiaDTO O DTO da garantia a ser salva.
     * @return O DTO da garantia salva.
     */
    public GarantiaDTO save (GarantiaDTO garantiaDTO){
        Garantia garantia = garantiaMapper.paraEntity(garantiaDTO);
        Garantia garantiaSalva = garantiaRepository.save(garantia);
        return garantiaMapper.paraDTO(garantiaSalva);
    }
    /**
     * Encontra uma garantia pelo ID.
     *
     * @param id O ID da garantia.
     * @return O DTO da garantia.
     * @throws GarantiaNotFoundException Se a garantia não for encontrada.
     */
    public GarantiaDTO findById (Long id){
        Garantia garantia = garantiaRepository.findById(id)
                .orElseThrow(() -> new GarantiaNotFoundException("Garantia não encontrada com ID: " + id));
        return garantiaMapper.paraDTO(garantia);
    }

    /**
     * Encontra todas as garantias.
     * return Uma lista de DTOs de garantias.
     */
    public List<GarantiaDTO> findAll(){
        List<Garantia> garantias = garantiaRepository.findAll();
        return garantias.stream()
                .map(garantiaMapper::paraDTO)
                .collect(Collectors.toList());
    }
    /**
     * Atualiza uma garantia existente.
     *
     * @param id               O ID da garantia a ser atualizada.
     * @param garantiaDTO      O DTO com os novos dados.
     * @return O DTO da garantia atualizada.
     * @throws GarantiaNotFoundException Se a garantia não for encontrada.
     */
    public GarantiaDTO update(Long id, GarantiaDTO garantiaDTO){
        Garantia garantiaExistente = garantiaRepository.findById(id)
                .orElseThrow(() -> new GarantiaNotFoundException("Garantia não encontrada com ID: " + id));

        garantiaExistente.setDescricao((garantiaDTO.getDescricao()));
        garantiaExistente.setValor(garantiaDTO.getValor());

        Garantia garantiaAtualizada = garantiaRepository.save(garantiaExistente);
         return garantiaMapper.paraDTO(garantiaAtualizada);
    }

    /**
     * Deleta uma garantia pelo ID.
     *
     * @param id O ID da garantia a ser deletada.
     * @throws GarantiaNotFoundException Se a garantia não for encontrada.
     *
     */
    public void delete(Long id){
        if(!garantiaRepository.existsById(id)){
            throw new GarantiaNotFoundException("Garantia não encontrada com ID: " + id );
        }
        garantiaRepository.deleteById(id);
    }
}

