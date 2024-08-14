package com.luazevedo.emprestimoBancarioII.service;

import com.luazevedo.emprestimoBancarioII.dto.PagamentoDTO;
import com.luazevedo.emprestimoBancarioII.entity.Pagamento;
import com.luazevedo.emprestimoBancarioII.exception.ValorNaoExistenteNaBaseDeDadosException;
import com.luazevedo.emprestimoBancarioII.mapper.PagamentoMapper;
import com.luazevedo.emprestimoBancarioII.repository.PagamentoRepository;
import com.luazevedo.emprestimoBancarioII.util.CalculoUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Serviço responsável pela gestão dos pagamentos.
 */
@Service
@RequiredArgsConstructor
public class PagamentoService {

    private final PagamentoRepository repository;
    private final PagamentoMapper mapper;

    /**
     * Recupera todos os pagamentos.
     *
     * @return Uma lista de {@link PagamentoDTO} contendo todos os pagamentos.
     */
    public List<PagamentoDTO> findAll() {
        return repository.findAll().stream()
                .map(mapper::paraDTO)
                .collect(Collectors.toList());
    }

    /**
     * Recupera um pagamento pelo seu identificador.
     *
     * @param id O identificador do pagamento.
     * @return Um {@link PagamentoDTO} contendo os detalhes do pagamento.
     * @throws ValorNaoExistenteNaBaseDeDadosException Se o pagamento com o identificador fornecido não for encontrado.
     */
    public PagamentoDTO findById(Long id) {
        Pagamento pagamento = repository.findById(id).orElseThrow(
                () -> new ValorNaoExistenteNaBaseDeDadosException("Pagamento com id " + id + " não foi encontrado"));
        return mapper.paraDTO(pagamento);
    }

    /**
     * Salva um novo pagamento.
     *
     * @param pagamentoDTO Os detalhes do pagamento a ser salvo.
     * @return O identificador do pagamento salvo.
     */
    public Long save(PagamentoDTO pagamentoDTO) {
        Pagamento pagamento = mapper.paraEntity(pagamentoDTO);
        return repository.save(pagamento).getId();
    }

    /**
     * Exclui um pagamento pelo seu identificador.
     *
     * @param id O identificador do pagamento a ser excluído.
     * @throws ValorNaoExistenteNaBaseDeDadosException Se o pagamento com o identificador fornecido não for encontrado.
     */
    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new ValorNaoExistenteNaBaseDeDadosException("Pagamento com id " + id + " não foi encontrado");
        }
    }

    /**
     * Atualiza um pagamento existente.
     *
     * @param pagamentoDTO Os detalhes atualizados do pagamento.
     * @return O identificador do pagamento atualizado.
     * @throws ValorNaoExistenteNaBaseDeDadosException Se o pagamento com o identificador fornecido não for encontrado.
     */
    public Long update(PagamentoDTO pagamentoDTO) {
        Pagamento pagamento = mapper.paraEntity(pagamentoDTO);
        if (repository.existsById(pagamento.getId())) {
            return repository.save(pagamento).getId();
        } else {
            throw new ValorNaoExistenteNaBaseDeDadosException("Pagamento com id " + pagamento.getId() + " não foi encontrado");
        }
    }

    /**
     * Calcula o valor total de um pagamento e salva o pagamento com o valor total calculado.
     *
     * @param pagamentoDTO Os detalhes do pagamento a ser calculado.
     * @return O {@link PagamentoDTO} com o valor total calculado e salvo.
     */
    public PagamentoDTO calcularPagamento(PagamentoDTO pagamentoDTO) {
        // Converte DTO para entidade
        Pagamento pagamento = mapper.paraEntity(pagamentoDTO);

        // Realiza o cálculo do valor total
        BigDecimal valorTotal = CalculoUtil.calcularJuros(
                pagamento.getValor(), // Corrigido para `valor`
                pagamento.getTaxaJuros(),
                pagamento.getPrazoMeses()
        );

        // Atualiza o valor total no pagamento
        pagamento.setValorTotal(valorTotal);

        // Salva o pagamento atualizado
        Pagamento pagamentoSalvo = repository.save(pagamento);

        // Converte a entidade salva de volta para DTO
        return mapper.paraDTO(pagamentoSalvo);
    }
}