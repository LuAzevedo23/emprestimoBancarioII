package com.luazevedo.emprestimoBancarioII.service;

import com.luazevedo.emprestimoBancarioII.dto.EmprestimoDTO;
import com.luazevedo.emprestimoBancarioII.entity.Emprestimo;
import com.luazevedo.emprestimoBancarioII.exception.EmprestimoNotFoundException;
import com.luazevedo.emprestimoBancarioII.mapper.EmprestimoMapper;
import com.luazevedo.emprestimoBancarioII.repository.EmprestimoRepository;
import com.luazevedo.emprestimoBancarioII.util.CalculoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Servicos para operações relacionadas a empréstimos.
 * Contém métodos para gerenciar operações de empréstimo, como cálculos de juros e parcelas.
 *
 * @see Emprestimo
 * @see EmprestimoDTO
 * @see EmprestimoMapper
 * @see CalculoUtil
 */

@Service
public class EmprestimoService {

    private final EmprestimoRepository emprestimoRepository;
    private final EmprestimoMapper emprestimoMapper;

    @Autowired
    public EmprestimoService(EmprestimoRepository emprestimoRepository, EmprestimoMapper emprestimoMapper) {
        this.emprestimoRepository = emprestimoRepository;
        this.emprestimoMapper = emprestimoMapper;
    }
    /**
     * Calcula o valor total do empréstimo.
     *
     * @param emprestimoDTO O DTO do empréstimo.
     * @return O DTO atualizado com o valor total calculado.
     */

    public EmprestimoDTO calcularValorTotal(EmprestimoDTO emprestimoDTO) {
        Emprestimo emprestimo = emprestimoMapper.paraEntity(emprestimoDTO);

        if(emprestimo.getValor() == null || emprestimo.getTaxaJuros() == null || emprestimo.getPrazoMeses() == null) {
         throw new IllegalArgumentException("Valor, taxa de juros e prazo devem ser fornecidos");
        }
        //Converte BigDecimal para double
        double valor = emprestimo.getValor().doubleValue();
        double taxaJuros = emprestimo.getTaxaJuros().doubleValue();

        BigDecimal valorTotal = CalculoUtil.calcularJuros(
                emprestimo.getValor(),
                emprestimo.getTaxaJuros(),
                emprestimo.getPrazoMeses()
        );
        emprestimo.setValorTotal(valorTotal);

        return emprestimoMapper.paraDTO(emprestimoRepository.save(emprestimo));
    }

    /**
     * Calcula o valor da parcela e o número total de parcelas para o empréstimo.
     *
     * @param emprestimo  O empréstimo.
     * @return O empréstimo atualizado com o valor da parcela e o número total das parcelas
     */

    public Emprestimo calcularParcelas(Emprestimo emprestimo) {
        // Validação para garantir que os campos essenciais não são nulos
        if (emprestimo.getValor() == null || emprestimo.getTaxaJuros() == null || emprestimo.getPrazoMeses() == null) {
            throw new IllegalArgumentException("Valor, taxa de juros e prazo devem ser fornecidos.");
        }
        //Converte BigDecimal para double
        double valor = emprestimo.getValor().doubleValue();
        double taxaJuros = emprestimo.getTaxaJuros().doubleValue()
;
        BigDecimal parcela = CalculoUtil.calcularParcela(
                BigDecimal.valueOf(valor),
                BigDecimal.valueOf(taxaJuros),
                emprestimo.getPrazoMeses()
        );
        emprestimo.setParcela(parcela);
        emprestimo.setNumeroParcelas(emprestimo.getPrazoMeses());

        return emprestimoRepository.save(emprestimo);
    }
    /**
     * Encontra um empréstimo pelo ID.
     *
     * @param id O ID do empréstimo.
     * @return O DTO do empréstimo.
     * @throws EmprestimoNotFoundException Se o empréstimo não for encontrado.
     */
    public EmprestimoDTO findById(Long id){
        Emprestimo emprestimo = emprestimoRepository.findById(id)
        .orElseThrow(() -> new EmprestimoNotFoundException("Empréstimo nõ encontrado com ID: " + id));
        return emprestimoMapper.paraDTO(emprestimo);
    }


    public List<EmprestimoDTO> findAll(){
        List<Emprestimo> emprestimos = emprestimoRepository.findAll();
        return emprestimos.stream()
                .map(emprestimoMapper::paraDTO)
                .collect(Collectors.toList());
    }
    /**
     * Salva um novo empréstimo ou atualiza um empréstimo existente.
     *
     * @param emprestimoDTO O DTO do empréstimo.
     * @return O DTO do empréstimo salvo ou atualizado.
     */
    public EmprestimoDTO save(EmprestimoDTO emprestimoDTO){
        Emprestimo emprestimo = emprestimoMapper.paraEntity(emprestimoDTO);
        Emprestimo emprestimoSalvo = emprestimoRepository.save(emprestimo);
        return emprestimoMapper.paraDTO(emprestimoSalvo);
    }
    /**
     * Atualiza um empréstimo existente.
     *
     * @param id O ID do empréstimo a ser atualizado.
     * @param emprestimoDTO O DTO do empréstimo com os novos dados.
     * @return O DTO do empréstimo atualizado.
     * @throws EmprestimoNotFoundException Se o empréstimo não for encontrado.
     */
    public EmprestimoDTO update(Long id, EmprestimoDTO emprestimoDTO){
        Emprestimo emprestimoExistente = emprestimoRepository.findById(id)
                .orElseThrow(() -> new EmprestimoNotFoundException("Empréstimo não encontrado com ID: " + id));

        emprestimoExistente.setValor(emprestimoDTO.getValor());
        emprestimoExistente.setTaxaJuros(emprestimoDTO.getTaxaJuros());
        emprestimoExistente.setPrazoMeses(emprestimoDTO.getPrazoMeses());
        emprestimoExistente.setNumeroParcelas(emprestimoDTO.getNumeroParcelas());
        emprestimoExistente.setParcela(emprestimoDTO.getParcela());
        emprestimoExistente.setDataEmprestimo(emprestimoDTO.getDataEmprestimo());
        emprestimoExistente.setDataTermino(emprestimoDTO.getDataTermino());
        emprestimoExistente.setValorTotal(emprestimoDTO.getValorTotal());

        Emprestimo emprestimoAtualizado = emprestimoRepository.save(emprestimoExistente);
        return emprestimoMapper.paraDTO(emprestimoAtualizado);
    }
    /**
     * Deleta um empréstimo pelo ID.
     *
     * @param id O ID do empréstimo a ser deletado.
     * @throws EmprestimoNotFoundException Se o empréstimo não for encontrado.
     *
     */
    public void delete(Long id){
        if (!emprestimoRepository.existsById(id)){
            throw new EmprestimoNotFoundException("Empréstimo não encontrado com ID: " + id);
        }
        emprestimoRepository.deleteById(id);
    }
    /**
     * Calcula o prazo entre duas datas e atualiza o DTO do empréstimo.
     *
     * @param emprestimoDTO O DTO do empréstimo com as datas de início e fim.
     * @return O DTO do empréstimo com o prazo calculado.
     */
    public EmprestimoDTO calcularPrazoEntreDatas(EmprestimoDTO emprestimoDTO) {
        Emprestimo emprestimo = emprestimoMapper.paraEntity(emprestimoDTO);

        // Validação para garantir que as datas não sejam nulas
        if (emprestimo.getDataEmprestimo() == null || emprestimo.getDataTermino() == null) {
            throw new IllegalArgumentException("As datas de início e término devem ser fornecidas.");
        }

        // Validação para garantir que a data de término seja posterior à data de início
        if (emprestimo.getDataTermino().isBefore(emprestimo.getDataEmprestimo())) {
            throw new IllegalArgumentException("A data de término não pode ser anterior à data de início.");
        }

        Integer prazoMeses = CalculoUtil.calcularPrazoEntreDatas(
                emprestimo.getDataEmprestimo(),
                emprestimo.getDataTermino()
        );
        emprestimo.setPrazoMeses(prazoMeses);

        return emprestimoMapper.paraDTO(emprestimo);
    }
}
