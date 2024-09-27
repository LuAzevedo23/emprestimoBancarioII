package com.luazevedo.emprestimoBancarioII.controller;

import com.luazevedo.emprestimoBancarioII.dto.EmprestimoDTO;
import com.luazevedo.emprestimoBancarioII.entity.Emprestimo;
import com.luazevedo.emprestimoBancarioII.service.EmprestimoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
@RequestMapping("/api/v1/emprestimos")
//@Api(value = "EmprestimoController", tags = "Emprestimos")
public class EmprestimoController {

    @Autowired
    private EmprestimoService emprestimoService;

    @Autowired
    public EmprestimoController(EmprestimoService emprestimoService) {
        this.emprestimoService = emprestimoService;
    }

    @Operation(description = "Busca todos os empréstimos", responses = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos"),
            @ApiResponse(responseCode = "401", description = "Você não está autorizado a ver este recurso"),
            @ApiResponse(responseCode = "403", description = "Acesso ao recurso proibido"),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar busca dos dados")
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