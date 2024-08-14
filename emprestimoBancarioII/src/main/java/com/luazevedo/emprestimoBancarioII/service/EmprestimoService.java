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


/**
 * Serviço para operações relacionadas a empréstimos.
 * <p>
 * Esta classe contém métodos para gerenciar operações de empréstimo, como cálculo de valor total do empréstimo.
 * </p>
 *
 * @see Emprestimo
 * @see EmprestimoDTO
 * @see EmprestimoMapper
 * @see CalculoUtil
 */
@Service
public class EmprestimoService {

    @Autowired
    private EmprestimoRepository emprestimoRepository;

    @Autowired
    private EmprestimoMapper mapper;

    /**
     * Calcula o valor total do empréstimo.
     *
     * @param emprestimoDTO O DTO do empréstimo.
     * @return O DTO atualizado com o valor total calculado.
     */
    public EmprestimoDTO calcularValorTotal(EmprestimoDTO emprestimoDTO) {
        // Converte DTO para entidade
        Emprestimo emprestimo = mapper.paraEntity(emprestimoDTO);

        // Calcula o valor total usando a utilitária
        BigDecimal valorTotal = CalculoUtil.calcularJuros(
                emprestimo.getValor(),
                emprestimo.getTaxaJuros(),
                emprestimo.getPrazoMeses()
        );

        // Atualiza o valor total no empréstimo
        emprestimo.setValorTotal(valorTotal);

        // Converte a entidade de volta para DTO
        return mapper.paraDTO(emprestimoRepository.save(emprestimo));
    }

    /**
     * Calcula o valor da parcela e o número total de parcelas para o empréstimo.
     *
     * @param emprestimo A entidade do empréstimo.
     * @return O empréstimo atualizado com o valor da parcela e o número total de parcelas.
     */
    public Emprestimo calcularParcelas(Emprestimo emprestimo) {
        // Calcula o valor da parcela usando a utilitária
        BigDecimal parcela = CalculoUtil.calcularParcela(
                emprestimo.getValor(),
                emprestimo.getTaxaJuros(),
                emprestimo.getPrazoMeses()
        );
        emprestimo.setParcela(parcela);
        emprestimo.setNumeroParcelas(emprestimo.getPrazoMeses());

        // Salva o empréstimo atualizado
        return emprestimoRepository.save(emprestimo);
    }

    /**
     * Encontra um empréstimo pelo ID.
     *
     * @param id O ID do empréstimo.
     * @return O DTO do empréstimo.
     * @throws EmprestimoNotFoundException Se o empréstimo não for encontrado.
     */
    public EmprestimoDTO findById(Long id) {
        Emprestimo emprestimo = emprestimoRepository.findById(id)
                .orElseThrow(() -> new EmprestimoNotFoundException("Empréstimo não encontrado com ID: " + id));
        return mapper.paraDTO(emprestimo);
    }

    /**
     * Salva um novo empréstimo ou atualiza um empréstimo existente.
     *
     * @param emprestimoDTO O DTO do empréstimo.
     * @return O DTO do empréstimo salvo ou atualizado.
     */
    public EmprestimoDTO save(EmprestimoDTO emprestimoDTO) {
        // Converte DTO para entidade
        Emprestimo emprestimo = mapper.paraEntity(emprestimoDTO);

        // Salva ou atualiza o empréstimo
        Emprestimo emprestimoSalvo = emprestimoRepository.save(emprestimo);

        // Converte a entidade de volta para DTO
        return mapper.paraDTO(emprestimoSalvo);
    }

    /**
     * Atualiza um empréstimo existente.
     *
     * @param id O ID do empréstimo a ser atualizado.
     * @param emprestimoDTO O DTO do empréstimo com os novos dados.
     * @return O DTO do empréstimo atualizado.
     * @throws EmprestimoNotFoundException Se o empréstimo não for encontrado.
     */
    public EmprestimoDTO update(Long id, Emprestimo emprestimoDTO) {
        Emprestimo emprestimoExistente = emprestimoRepository.findById(id)
                .orElseThrow(() -> new EmprestimoNotFoundException("Empréstimo não encontrado com ID: " + id));

        // Atualiza os dados do empréstimo existente com os dados do DTO
        emprestimoExistente.setValor(emprestimoDTO.getValor());
        emprestimoExistente.setTaxaJuros(emprestimoDTO.getTaxaJuros());
        emprestimoExistente.setPrazoMeses(emprestimoDTO.getPrazoMeses());
        emprestimoExistente.setNumeroParcelas(emprestimoDTO.getNumeroParcelas());
        emprestimoExistente.setParcela(emprestimoDTO.getParcela());
        emprestimoExistente.setDataEmprestimo(emprestimoDTO.getDataEmprestimo());
        emprestimoExistente.setDataTermino(emprestimoDTO.getDataTermino());
        emprestimoExistente.setValorTotal(emprestimoDTO.getValorTotal());

        Emprestimo emprestimoAtualizado = emprestimoRepository.save(emprestimoExistente);
        return mapper.paraDTO(emprestimoAtualizado);
    }

    /**
     * Deleta um empréstimo pelo ID.
     *
     * @param id O ID do empréstimo a ser deletado.
     * @throws EmprestimoNotFoundException Se o empréstimo não for encontrado.
     */
    public void delete(Long id) {
        if (!emprestimoRepository.existsById(id)) {
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
        // Converte DTO para entidade
        Emprestimo emprestimo = mapper.paraEntity(emprestimoDTO);

        // Verifica se as datas são nulas para evitar NullPointerException
        if (emprestimo.getDataEmprestimo() == null || emprestimo.getDataTermino() == null) {
            throw new IllegalArgumentException("Datas de início e fim devem ser fornecidas.");
        }

        // Calcula o prazo entre as datas
        Integer prazoMeses = CalculoUtil.calcularPrazoEntreDatas(
                emprestimo.getDataEmprestimo(),
                emprestimo.getDataTermino()
        );

        // Atualiza o prazo no empréstimo
        emprestimo.setPrazoMeses(prazoMeses);

        // Converte a entidade de volta para DTO
        return mapper.paraDTO(emprestimo);
    }
}