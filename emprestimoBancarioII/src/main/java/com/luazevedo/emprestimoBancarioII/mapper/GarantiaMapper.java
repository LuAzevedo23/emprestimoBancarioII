package com.luazevedo.emprestimoBancarioII.mapper;

import com.luazevedo.emprestimoBancarioII.dto.GarantiaDTO;
import com.luazevedo.emprestimoBancarioII.entity.Garantia;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface GarantiaMapper {

GarantiaMapper INSTANCE = Mappers.getMapper(GarantiaMapper.class);

GarantiaDTO paraDTO(Garantia garantia);

Garantia paraEntity(GarantiaDTO garantiaDTO);

List<GarantiaDTO> paraDTO(List<Garantia> garantias);

List<Garantia> paraEntity(List<GarantiaDTO> GarantiaDTO);

}
