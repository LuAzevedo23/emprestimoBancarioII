package com.luazevedo.emprestimoBancarioII.controller;

import com.luazevedo.emprestimoBancarioII.dto.EmprestimoDTO;
import com.luazevedo.emprestimoBancarioII.entity.Emprestimo;
import com.luazevedo.emprestimoBancarioII.exception.EmprestimoNotFoundException;
import com.luazevedo.emprestimoBancarioII.service.EmprestimoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

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
@Api(value = "EmprestimoController", tags = "Emprestimos")
public class EmprestimoController {

    @Autowired
    private EmprestimoService emprestimoService;

    @Operation(summary = "Busca todos os empréstimos", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Busca realizada com sucesso"),
            @ApiResponse(code = 400, message = "Parâmetros inválidos"),
            @ApiResponse(code = 401, message = "Você não está autorizado a ver este recurso"),
            @ApiResponse(code = 403, message = "Acesso ao recurso proibido"),
            @ApiResponse(code = 404, message = "Recurso não encontrado"),
            @ApiResponse(code = 422, message = "Dados de requisição inválidos"),
            @ApiResponse(code = 500, message = "Erro ao realizar busca dos dados")
    })

    @GetMapping
    public ResponseEntity<List<EmprestimoDTO>> findAll() {
        List<EmprestimoDTO> emprestimos = emprestimoService.findAll();
        return ResponseEntity.ok(emprestimos);
    }

    @PostMapping("/calcular-parcelas")
    public ResponseEntity<Emprestimo> calcularParcelas(@RequestBody Emprestimo emprestimo) {
        Emprestimo emprestimoCalculado = emprestimoService.calcularParcelas(emprestimo);
        return ResponseEntity.ok(emprestimoCalculado);
    }

    @GetMapping("/calcular-prazo")
    public ResponseEntity<EmprestimoDTO> calcularPrazo(@RequestParam("dataInicial") LocalDate dataInicial,
                                                       @RequestParam("dataFinal") LocalDate dataFinal) {
        EmprestimoDTO emprestimoDTO = new EmprestimoDTO();
        emprestimoDTO.setDataEmprestimo(dataInicial);
        emprestimoDTO.setDataTermino(dataFinal);

        EmprestimoDTO emprestimoCalculado = emprestimoService.calcularPrazoEntreDatas(emprestimoDTO);
        return ResponseEntity.ok(emprestimoCalculado);
    }

    @PostMapping
    public ResponseEntity<Long> save(@RequestBody EmprestimoDTO emprestimoDTO) {
        Long id = emprestimoService.save(emprestimoDTO).getId();
        return ResponseEntity.ok(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmprestimoDTO> findById(@PathVariable Long id) {
        EmprestimoDTO emprestimo = emprestimoService.findById(id); // Apenas chama o método, sem orElseThrow
        return ResponseEntity.ok(emprestimo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmprestimoDTO> update(@PathVariable Long id, @RequestBody EmprestimoDTO emprestimoDTO) {
        EmprestimoDTO emprestimoAtualizado = emprestimoService.update(id, emprestimoDTO);
        return ResponseEntity.ok(emprestimoAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        emprestimoService.delete(id);
        return ResponseEntity.ok("Empréstimo deletado com sucesso.");
    }
}