package com.luazevedo.emprestimoBancarioII.mapper;

import com.luazevedo.emprestimoBancarioII.dto.HistoricoEmprestimoDTO;
import com.luazevedo.emprestimoBancarioII.entity.HistoricoEmprestimo;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface HistoricoEmprestimoMapper {

    HistoricoEmprestimoMapper INSTANCE = Mappers.getMapper(HistoricoEmprestimoMapper.class);

    HistoricoEmprestimoDTO paraDTO(HistoricoEmprestimo historicoEmprestimo);

    HistoricoEmprestimo paraEntity(HistoricoEmprestimoDTO historicoEmprestimoDTO);

    List<HistoricoEmprestimoDTO> paraDTO(List<HistoricoEmprestimo> historicoEmprestimos);

    List<HistoricoEmprestimo> paraEntity(List<HistoricoEmprestimoDTO> HistoricoEmprestimoDTO);

}
