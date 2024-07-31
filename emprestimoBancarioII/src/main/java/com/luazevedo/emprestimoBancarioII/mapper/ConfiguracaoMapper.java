package com.luazevedo.emprestimoBancarioII.mapper;

import com.luazevedo.emprestimoBancarioII.dto.ConfiguracaoDTO;
import com.luazevedo.emprestimoBancarioII.entity.Configuracao;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ConfiguracaoMapper {

ConfiguracaoMapper INSTANCE = Mappers.getMapper(ConfiguracaoMapper.class);

ConfiguracaoDTO paraDTO(Configuracao configuracao);

Configuracao paraEntity(ConfiguracaoDTO configuracaoDTO);

List<ConfiguracaoDTO> paraDTO(List<Configuracao> configuracao);

List<Configuracao> paraEntity(List<ConfiguracaoDTO> configuracaoDTO);

}