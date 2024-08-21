package com.luazevedo.emprestimoBancarioII.controller;

import com.luazevedo.emprestimoBancarioII.dto.EmprestimoDTO;
import com.luazevedo.emprestimoBancarioII.entity.Emprestimo;
import com.luazevedo.emprestimoBancarioII.service.EmprestimoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

/**
 * Controlador para operações relacionadas a empréstimos.
 *
 * <p>Esta classe lida com as requisições HTTP relacionadas a empréstimos, como cálculo de parcelas,
 * cálculo de prazo entre datas, criação, atualização, exclusão e recuperação de empréstimos.</p>
 *
 * @author Luciene Azevedo
 * @see EmprestimoService
 * @see Emprestimo
 */
@RestController
@RequestMapping("/emprestimos")
@RequiredArgsConstructor
@Api(value = "EmprestimoController", tags = "Emprestimos")
public class EmprestimoController {

    @Autowired
    private EmprestimoService emprestimoService;

    @Operation(summary = "Busca todos os emprestimos", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Busca realizada com sucesso"),
            @ApiResponse(code = 400, message = "Parametros inválidos"),
            @ApiResponse(code = 401, message = "Você não está autorizado a ver este recurso"),
            @ApiResponse(code = 403, message = "Acesso ao recurso proibido"),
            @ApiResponse(code = 404, message = "Recurso não encontrado"),
            @ApiResponse(code = 422, message = "Dados de requisição inválida"),
            @ApiResponse(code = 500, message = "Erro ao realizar busca dos dados")
    })

    /**
     * Calcula as parcelas de um empréstimo com base nos parâmetros fornecidos.
     * Utiliza o serviço {@link EmprestimoService} para realizar os cálculos.
     *
     * @param emprestimo Os dados do empréstimo para cálculo das parcelas.
     * @return ResponseEntity com o empréstimo com o valor da parcela calculado.
     */
    @PostMapping("/calcular-parcelas")
    public ResponseEntity<Emprestimo> calcularParcelas(@RequestBody Emprestimo emprestimo) {
        Emprestimo emprestimoCalculado = emprestimoService.calcularParcelas(emprestimo);
        return ResponseEntity.ok(emprestimoCalculado);
    }

    /**
     * Calcula o prazo entre duas datas.
     * Utiliza o serviço {@link EmprestimoService} para calcular a diferença em meses.
     *
     * @param dataInicial A data inicial.
     * @param dataFinal A data final.
     * @return O DTO do empréstimo com o prazo calculado.
     */
    @GetMapping("/calcular-prazo")
    public ResponseEntity<EmprestimoDTO> calcularPrazo(@RequestParam("dataInicial") LocalDate dataInicial,
                                                       @RequestParam("dataFinal") LocalDate dataFinal) {
        // Cria um DTO com as datas fornecidas
        EmprestimoDTO emprestimoDTO = new EmprestimoDTO();
        emprestimoDTO.setDataEmprestimo(dataInicial);
        emprestimoDTO.setDataTermino(dataFinal);

        // Calcula o prazo entre as datas
        EmprestimoDTO emprestimoCalculado = emprestimoService.calcularPrazoEntreDatas(emprestimoDTO);

        return ResponseEntity.ok(emprestimoCalculado);
    }
        @PostMapping
    public Long save(@RequestBody EmprestimoDTO emprestimoDTO) {
        return emprestimoService.save(emprestimoDTO).getId();
    }

    /**
     * Encontra um empréstimo pelo ID.
     *
     * @param id O ID do empréstimo.
     * @return ResponseEntity com o empréstimo encontrado.
     */
    @GetMapping("/{id}")
    public ResponseEntity<EmprestimoDTO> findById(@PathVariable Long id) {
        EmprestimoDTO emprestimo = emprestimoService.findById(id);
        return ResponseEntity.ok(emprestimo);
    }

    /**
     * Atualiza um empréstimo existente.
     *
     * @param id         O ID do empréstimo a ser atualizado.
     * @param emprestimo O empréstimo com os novos dados.
     * @return ResponseEntity com o empréstimo atualizado.
     */
    @PutMapping("/{id}")
    public ResponseEntity<EmprestimoDTO> update(@PathVariable Long id, @RequestBody Emprestimo emprestimo) {
        EmprestimoDTO emprestimoAtualizado = emprestimoService.update(id, emprestimo);
        return ResponseEntity.ok(emprestimoAtualizado);
    }

    /**
     * Deleta um empréstimo pelo ID.
     *
     * @param id O ID do empréstimo a ser deletado.
     * @return ResponseEntity com uma mensagem de sucesso.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        emprestimoService.delete(id);
        return ResponseEntity.ok("Empréstimo deletado com sucesso.");
    }
}