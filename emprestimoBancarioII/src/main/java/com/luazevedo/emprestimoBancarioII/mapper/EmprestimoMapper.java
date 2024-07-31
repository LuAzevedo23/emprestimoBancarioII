package com.luazevedo.emprestimoBancarioII.mapper;

import com.luazevedo.emprestimoBancarioII.dto.EmprestimoDTO;
import com.luazevedo.emprestimoBancarioII.entity.Emprestimo;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EmprestimoMapper {

    EmprestimoMapper INSTANCE = Mappers.getMapper(EmprestimoMapper.class);

    EmprestimoDTO paraDTO(Emprestimo emprestimo);

    Emprestimo paraEntity(EmprestimoDTO emprestimoDTO);

    List<EmprestimoDTO> paraDTO(List<Emprestimo> emprestimos);

    List<Emprestimo> paraEntity(List<EmprestimoDTO> EmprestimoDTO);

}

