package com.luazevedo.emprestimoBancarioII.mapper;

import com.luazevedo.emprestimoBancarioII.dto.PagamentoDTO;
import com.luazevedo.emprestimoBancarioII.entity.Pagamento;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PagamentoMapper {

    PagamentoMapper INSTANCE = Mappers.getMapper(PagamentoMapper.class);

    PagamentoDTO paraDTO(Pagamento pagamento);

    Pagamento paraEntity(PagamentoDTO pagamentoDTO);

    List<PagamentoDTO> paraDTO(List<Pagamento> pagamentos);

    List<Pagamento> paraEntity(List<PagamentoDTO> PagamentoDTO);
}

