package com.luazevedo.emprestimoBancarioII.mapper;

import com.luazevedo.emprestimoBancarioII.dto.GarantiaDTO;
import com.luazevedo.emprestimoBancarioII.entity.Garantia;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface GarantiaMapper {

    GarantiaMapper INSTANCE = Mappers.getMapper(GarantiaMapper.class);

    /**
     * Converte um GarantiaDTO para uma entidade Garantia.
     *
     * @param garantiaDTO o DTO a ser convertido
     * @return a entidade Garantia
     */
    Garantia paraEntity(GarantiaDTO garantiaDTO);

    /**
     * Converte uma entidade Garantia para um GarantiaDTO.
     *
     * @param garantia a entidade a ser convertida
     * @return o DTO Garantia
     */
    @Mapping(target = "descricao", ignore = true)
    @Mapping(target = "valor", ignore = true)
    GarantiaDTO paraDTO(Garantia garantia);

    List<GarantiaDTO> paraDTO(List<Garantia> garantias);

    /**
     * Atualiza uma entidade Garantia com os dados de um GarantiaDTO.
     *
     * @param garantiaDTO os dados do DTO
     * @param garantia a entidade a ser atualizada
     */
    void atualizarEntity(GarantiaDTO garantiaDTO, @MappingTarget Garantia garantia);

}
