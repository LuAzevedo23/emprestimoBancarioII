package com.luazevedo.emprestimoBancarioII.service;

import com.luazevedo.emprestimoBancarioII.dto.PagamentoDTO;
import com.luazevedo.emprestimoBancarioII.entity.Pagamento;
import com.luazevedo.emprestimoBancarioII.exception.PagamentoNotFoundException;
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
 * Serviço para operações relacionadas aos pagamentos.
 * Contém métodos para gerenciar operações de pagamento.
 *
 * @see Pagamento
 * @see PagamentoDTO
 * @see PagamentoMapper
 */
@Service
public class PagamentoService {

    private final PagamentoRepository pagamentoRepository;
    private final PagamentoMapper pagamentoMapper;

    public PagamentoService(PagamentoRepository pagamentoRepository, PagamentoMapper pagamentoMapper){
      this.pagamentoRepository = pagamentoRepository;
      this.pagamentoMapper = pagamentoMapper;
    }

/**
 * Salva um novo pagamento.
 *
 * @param pagamentoDTO os dados do pagamento a serem salvos.
 * @return o pagamento salvo como DTO
 */
public Long save(PagamentoDTO pagamentoDTO){
    Pagamento pagamento = pagamentoMapper.paraEntity(pagamentoDTO);
    Pagamento savedPagamento = pagamentoRepository.save(pagamento);
return savedPagamento.getId();
}
/**
 * Atualiza um pagamento existente.
 *
 * @param id       o ID do pagamento a ser atualizado
 * @param pagamentoDTO os novos dados do pagamento
 * @return o pagamento atualizado como DTO
 */
public Long update(Long id, PagamentoDTO pagamentoDTO) {
    Pagamento pagamentoExistente = pagamentoRepository.findById(id)
            .orElseThrow(() -> new PagamentoNotFoundException("Pagamento não encontrado com ID: " + id));

    // Atualiza os campos do pagamento existente com os dados do DTO
    pagamentoMapper.atualizarPagamentoComDTO(pagamentoDTO, pagamentoExistente);
    pagamentoRepository.save(pagamentoExistente); // Salva a entidade atualizada

    return pagamentoExistente.getId(); // Retorna o ID do pagamento atualizado
}
/**
 * Encontra um pagamento pelo ID.
 *
 * @param id o OD do pagamento a ser encontrado
 * @return o pagamento encontrado como DTO
 */
public PagamentoDTO findById(Long id) {
    Pagamento pagamento = pagamentoRepository.findById(id)
            .orElseThrow(() -> new PagamentoNotFoundException("Pagamento não encontrado com ID: " + id));
    return pagamentoMapper.paraDTO(pagamento);
}
/**
 * Encontra todos os pagamentos.
 *
 * @return uma lista de todos os pagamentos como DTOs
 */
public List<PagamentoDTO> findAll() {
    List<Pagamento> pagamentos = pagamentoRepository.findAll();
    return pagamentos.stream()
            .map(pagamentoMapper::paraDTO)
            .toList();
}
    public void delete(Long id) {
        if (!pagamentoRepository.existsById(id)) {
            throw new PagamentoNotFoundException("Pagamento não encontrado com ID: " + id);
        }
        pagamentoRepository.deleteById(id); // Deleta o pagamento
    }
/**
 * Calcula o pagamento a partir dos dados fornecidos no DTO.
 *
 * @param pagamentoDTO os dados do pagamento a serem utilizados no cálculo
 * @return o pagamento calculado como DTO
 */
public PagamentoDTO calcularPagamento (PagamentoDTO pagamentoDTO){
    //Converte DTO para entidade
    Pagamento pagamento = pagamentoMapper.paraEntity(pagamentoDTO);

    //Realiza o cálculo do valor total

    BigDecimal valorTotal = CalculoUtil.calcularJuros(
            pagamento.getValor(),
            pagamento.getTaxaJuros(),
            pagamento.getPrazoMeses()
    );
    //Atualiza o valor total no pagamento
    pagamento.setValorTotal(valorTotal);

    //Salva o pagamento atualizado
    Pagamento pagamentoSalvo = pagamentoRepository.save(pagamento);

    //Converte a entidade salva de volta para DTO
    return pagamentoMapper.paraDTO(pagamentoSalvo);
}
}
