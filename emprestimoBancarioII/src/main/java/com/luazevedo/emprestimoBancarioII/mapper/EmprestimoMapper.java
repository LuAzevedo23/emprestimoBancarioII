package com.luazevedo.emprestimoBancarioII.mapper;

import com.luazevedo.emprestimoBancarioII.dto.EmprestimoDTO;
import com.luazevedo.emprestimoBancarioII.entity.Emprestimo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface EmprestimoMapper {

    /**
     * Instância do mapper.
     */
    EmprestimoMapper INSTANCE = Mappers.getMapper(EmprestimoMapper.class);

    /**
     * Converte uma entidade {@link Emprestimo} em um DTO {@link EmprestimoDTO}.
     *
     * @param entity A entidade de empréstimo.
     * @return O DTO de empréstimo.
     */
    @Mapping(source = "valor", target = "valor")
    @Mapping(source = "taxaJuros", target = "taxaJuros")
    @Mapping(source = "prazoMeses", target = "prazoMeses")
    @Mapping(source = "numeroParcelas", target = "numeroParcelas")
    @Mapping(source = "parcela", target = "parcela")
    @Mapping(source = "dataEmprestimo", target = "dataEmprestimo")
    @Mapping(source = "dataTermino", target = "dataTermino")
    @Mapping(source = "valorTotal", target = "valorTotal") // Adicione mapeamento ausente
    EmprestimoDTO paraDTO(Emprestimo entity);

    /**
     * Converte um DTO {@link EmprestimoDTO} em uma entidade {@link Emprestimo}.
     *
     * @param dto O DTO de empréstimo.
     * @return A entidade de empréstimo.
     */
    @Mapping(source = "valor", target = "valor")
    @Mapping(source = "taxaJuros", target = "taxaJuros")
    @Mapping(source = "prazoMeses", target = "prazoMeses")
    @Mapping(source = "numeroParcelas", target = "numeroParcelas")
    @Mapping(source = "parcela", target = "parcela")
    @Mapping(source = "dataEmprestimo", target = "dataEmprestimo")
    @Mapping(source = "dataTermino", target = "dataTermino")
    @Mapping(source = "valorTotal", target = "valorTotal") // Adicione mapeamento ausente
    @Mapping(target = "id", ignore = true) // Ajuste conforme necessário
    Emprestimo paraEntity(EmprestimoDTO dto);

    /**
     * Atualiza uma entidade {@link Emprestimo} com os valores de um DTO {@link EmprestimoDTO}.
     * <p>
     * Este método ignora o ID da entidade durante a atualização, pois o ID não deve ser alterado.
     * </p>
     *
     * @param emprestimoDTO O DTO com os dados atualizados.
     * @param emprestimo    A entidade que será atualizada.
     */
    @Mapping(target = "id", ignore = true) // Ignora o ID na atualização
    void updateEntityFromDTO(EmprestimoDTO emprestimoDTO, @MappingTarget Emprestimo emprestimo);
}
