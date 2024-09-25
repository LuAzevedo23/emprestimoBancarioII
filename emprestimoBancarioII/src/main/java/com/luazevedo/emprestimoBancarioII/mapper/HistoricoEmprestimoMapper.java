package com.luazevedo.emprestimoBancarioII.mapper;

import com.luazevedo.emprestimoBancarioII.dto.HistoricoEmprestimoDTO;
import com.luazevedo.emprestimoBancarioII.entity.HistoricoEmprestimo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

/**
 * Mapper para converter entre HistoricoEmprestimo e HistoricoEmprestimoDTO.
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface HistoricoEmprestimoMapper {

    HistoricoEmprestimoMapper INSTANCE = Mappers.getMapper(HistoricoEmprestimoMapper.class);

    /**
     * Converte um DTO HistoricoEmprestimoDTO para uma entidade.
     *
     * @param historicoEmprestimoDTO O DTO a ser convertido.
     * @return A entidade correspondente.
     */
    @Mapping(target = "dataEvento", source = "dataEvento", qualifiedByName = "localDateToInstant")
    HistoricoEmprestimo paraEntity(HistoricoEmprestimoDTO historicoEmprestimoDTO);

    /**
     * Converte uma entidade HistoricoEmprestimo para um DTO.
     *
     * @param historicoEmprestimo A entidade a ser convertida.
     * @return O DTO correspondente.
     */
    @Mapping(target = "descricao", ignore = true)
    @Mapping(target = "dataEvento", ignore = true)
    HistoricoEmprestimoDTO paraDTO(HistoricoEmprestimo historicoEmprestimo);

    /**
     * Método para converter LocalDate para Instant.
     *
     * @param localDate O LocalDate a ser convertido.
     * @return O Instant correspondente.
     */
    @Named("localDateToInstant")
    default Instant localDateToInstant(LocalDate localDate) {
        return localDate != null ? localDate.atStartOfDay(ZoneId.systemDefault()).toInstant() : null;
    }

    /**
     * Método para converter Instant para LocalDate.
     *
     * @param instant O Instant a ser convertido.
     * @return O LocalDate correspondente.
     */
    @Named("instantToLocalDate")
    default LocalDate instantToLocalDate(Instant instant) {
        return instant != null ? Instant.ofEpochMilli(instant.toEpochMilli()).atZone(ZoneId.systemDefault()).toLocalDate() : null;
    }
}