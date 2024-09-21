package com.luazevedo.emprestimoBancarioII.controller;

import com.luazevedo.emprestimoBancarioII.dto.HistoricoEmprestimoDTO;
import com.luazevedo.emprestimoBancarioII.service.HistoricoEmprestimoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Controlador para gerenciar operações relacionadas ao Histórico de Empréstimos.
 * Fornece endpoints para criação, atualização, exclusão e listagem de históricos.
 *
 * @see HistoricoEmprestimoService
 */
@RestController
@RequestMapping("/api/historico-emprestimos")
//@Api(value = "HistoricoEmprestimoController", tags = "Historico-Emprestimos")
public class HistoricoEmprestimoController {

    private HistoricoEmprestimoService historicoEmprestimoService;

    @Autowired
    public HistoricoEmprestimoController(HistoricoEmprestimoService historicoEmprestimoService) {
        this.historicoEmprestimoService = historicoEmprestimoService;
    }

    @Operation(description = "Retorna todos os historicos de empréstimos", responses = {
            @ApiResponse(responseCode = "200", description = "Historicos de empréstimos retornados com sucesso"),
            @ApiResponse(responseCode = "401", description = "Você não está autorizado a ver este recurso"),
            @ApiResponse(responseCode = "403", description = "Acesso ao recurso proibido"),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar busca dos dados")
    })

    /**
     * Retorna uma lista de todos os histtóricos de empréstimos.
     *
     * @return Um lista de DTOs de Histórico de Empréstimos.
     */

    @GetMapping
    public List<HistoricoEmprestimoDTO> findAll() {
        return historicoEmprestimoService.findAll();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<HistoricoEmprestimoDTO> findById(@PathVariable Long id) {
        HistoricoEmprestimoDTO dto = historicoEmprestimoService.findById(id);
        return org.springframework.http.ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<String> save(@RequestBody HistoricoEmprestimoDTO historicoEmprestimoDTO) {
        historicoEmprestimoService.save(historicoEmprestimoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("HistoricoEmprestimo salvo com sucesso");
    }

    /**
     * Atualiza um histório de empréstimo existente.
     *
     * @param id                     O DTO do histórico a ser atualizado.
     * @param historicoEmprestimoDTO O DTO com os novos dados.
     * @return Uma resposta com status de sucesso,
     */
    @PutMapping(value = "/{id}")
    public ResponseEntity<String> update(@PathVariable Long id, @RequestBody HistoricoEmprestimoDTO historicoEmprestimoDTO) {
        historicoEmprestimoService.update(id, historicoEmprestimoDTO);
        return ResponseEntity.status(HttpStatus.OK).body("Histórico de Empréstimo atualizado com sucesso!");
    }


    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        historicoEmprestimoService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("Histórico de Empréstimo excluido com sucesso");
    }
}


