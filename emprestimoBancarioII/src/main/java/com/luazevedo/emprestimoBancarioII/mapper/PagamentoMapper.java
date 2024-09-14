package com.luazevedo.emprestimoBancarioII.mapper;

import com.luazevedo.emprestimoBancarioII.dto.PagamentoDTO;
import com.luazevedo.emprestimoBancarioII.entity.Pagamento;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

/**
 * Mapper para converter entre Pagamento e PagamentoDTO.
 */
@Mapper(componentModel = "spring")
public interface PagamentoMapper {

    /**
     * Converte um PagamentoDTO para uma entidade Pagamento.
     *
     * @param pagamentoDTO O DTO do pagamento a ser convertido.
     * @return A entidade Pagamento correspondente.
     */
    default Pagamento paraEntity(PagamentoDTO pagamentoDTO) {
        if (pagamentoDTO == null) {
            return null;
        }
        Pagamento pagamento = new Pagamento();
        pagamento.setId(pagamentoDTO.getId());
        pagamento.setValor(pagamentoDTO.getValor());
        pagamento.setDataPagamento(pagamentoDTO.getDataPagamento());
        return pagamento;
    }

    /**
     * Converte uma entidade Pagamento para um PagamentoDTO.
     *
     * @param pagamento A entidade Pagamento a ser convertida.
     * @return O DTO do pagamento correspondente.
     */
    public default PagamentoDTO paraDTO(Pagamento pagamento) {
        if (pagamento == null){
            return null;
        }
        PagamentoDTO pagamentoDTO = new PagamentoDTO();
        pagamentoDTO.setId(pagamento.getId());
        pagamentoDTO.setValor(pagamento.getValor());
        pagamentoDTO.setDataPagamento(pagamento.getDataPagamento());
        return pagamentoDTO;
    }
    @Mapping(source = "emprestimoId", target = "emprestimo.id")
    @Mapping(target = "valorTotal", source = "pagamentoDTO.valorTotal") // Se pagamentoDTO tiver um campo valorTotal
    void atualizarPagamentoComDTO(PagamentoDTO pagamentoDTO, @MappingTarget Pagamento pagamentoExistente);
}

