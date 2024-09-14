package com.luazevedo.emprestimoBancarioII.controller;

import com.luazevedo.emprestimoBancarioII.dto.HistoricoEmprestimoDTO;
import com.luazevedo.emprestimoBancarioII.exception.AbstractMinhaException;
import com.luazevedo.emprestimoBancarioII.json.response.ExceptionResponse;
import com.luazevedo.emprestimoBancarioII.repository.HistoricoEmprestimoRepository;
import com.luazevedo.emprestimoBancarioII.service.HistoricoEmprestimoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

/**
 * Controlador para gerenciar operações relacionadas ao Histórico de Empréstimos.
 * Fornece endpoints para criação, atualização, exclusão e listagem de históricos.
 *
 * @see HistoricoEmprestimoService
 */
@RestController
@RequestMapping("/api/historico-emprestimos")
@Api(value = "HistoricoEmprestimoController", tags = "Historico-Emprestimos")
public class HistoricoEmprestimoController {

    private HistoricoEmprestimoService historicoEmprestimoService;

    @Autowired
    public HistoricoEmprestimoController(HistoricoEmprestimoService historicoEmprestimoService) {
        this.historicoEmprestimoService = historicoEmprestimoService;
    }

    @ApiOperation(value = "Retorna todos os historicos de empréstimos", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Historicos de empréstimos retornados com sucesso"),
            @ApiResponse(code = 401, message = "Você não está autorizado a ver este recurso"),
            @ApiResponse(code = 403, message = "Acesso ao recurso proibido"),
            @ApiResponse(code = 404, message = "Recurso não encontrado"),
            @ApiResponse(code = 422, message = "Dados de requisição inválida"),
            @ApiResponse(code = 500, message = "Erro ao realizar busca dos dados")
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


