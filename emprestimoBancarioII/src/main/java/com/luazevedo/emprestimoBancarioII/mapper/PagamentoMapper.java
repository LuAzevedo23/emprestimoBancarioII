package com.luazevedo.emprestimoBancarioII.mapper;

import com.luazevedo.emprestimoBancarioII.dto.PagamentoDTO;
import com.luazevedo.emprestimoBancarioII.entity.Pagamento;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * Mapper para converter entre {@link Pagamento} e {@link PagamentoDTO}.
 */
@Mapper(componentModel = "spring")
public interface PagamentoMapper {

    /**
     * Instância do mapper.
     */
    PagamentoMapper INSTANCE = Mappers.getMapper(PagamentoMapper.class);

    /**
     * Converte uma entidade {@link Pagamento} em um DTO {@link PagamentoDTO}.
     *
     * @param pagamento A entidade de pagamento.
     * @return O DTO de pagamento.
     */
    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "valor", target = "valor"),
            @Mapping(source = "dataPagamento", target = "dataPagamento"),
            @Mapping(source = "emprestimo.id", target = "emprestimoId"),
            @Mapping(source = "status", target = "status"),
            @Mapping(source = "valorTotal", target = "valorPrincipal"), // Ajuste baseado na lógica de negócio
            @Mapping(source = "taxaJuros", target = "taxaJuros"),
            @Mapping(source = "prazoMeses", target = "prazoMeses")
    })
    PagamentoDTO paraDTO(Pagamento pagamento);

    /**
     * Converte um DTO {@link PagamentoDTO} em uma entidade {@link Pagamento}.
     *
     * @param pagamentoDTO O DTO de pagamento.
     * @return A entidade de pagamento.
     */
    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "valor", target = "valor"),
            @Mapping(source = "dataPagamento", target = "dataPagamento"),
            @Mapping(source = "emprestimoId", target = "emprestimo.id"),
            @Mapping(source = "status", target = "status"),
            @Mapping(source = "valorPrincipal", target = "valorTotal"), // Ajuste baseado na lógica de negócio
            @Mapping(source = "taxaJuros", target = "taxaJuros"),
            @Mapping(source = "prazoMeses", target = "prazoMeses")
    })
    Pagamento paraEntity(PagamentoDTO pagamentoDTO);
}