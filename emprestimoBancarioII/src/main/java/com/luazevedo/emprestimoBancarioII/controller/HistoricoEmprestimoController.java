package com.luazevedo.emprestimoBancarioII.controller;

import com.luazevedo.emprestimoBancarioII.dto.HistoricoEmprestimoDTO;
import com.luazevedo.emprestimoBancarioII.entity.HistoricoEmprestimo;
import com.luazevedo.emprestimoBancarioII.exception.AbstractMinhaException;
import com.luazevedo.emprestimoBancarioII.json.response.ExceptionResponse;
import com.luazevedo.emprestimoBancarioII.mapper.HistoricoEmprestimoMapper;
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

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/historico_emprestimos")
@Api(value = "HistoricoEmprestimoController", tags = "Historico_Emprestimos")
public class HistoricoEmprestimoController {

    @Autowired
    HistoricoEmprestimoService service;
    @Autowired
    private HistoricoEmprestimoRepository repository;
    @Autowired
    private HistoricoEmprestimoMapper mapper;

    @ApiOperation(value = "Retorna todos os historicos de empréstimos", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Historicos de empréstimos retornados com sucesso"),
            @ApiResponse(code = 401, message = "Você não está autorizado a ver este recurso"),
            @ApiResponse(code = 403, message = "Acesso ao recurso proibido"),
            @ApiResponse(code = 404, message = "Recurso não encontrado"),
            @ApiResponse(code = 422, message = "Dados de requisição inválida"),
            @ApiResponse(code = 500, message = "Erro ao realizar busca dos dados")
    })

    @GetMapping
    public List<HistoricoEmprestimoDTO> findAll() {
        List<HistoricoEmprestimo> emprestimos = repository.findAll();
        return mapper.paraDTO(emprestimos);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<HistoricoEmprestimoDTO> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<String> save(@RequestBody HistoricoEmprestimoDTO historicoEmprestimoDTO) {
        service.save(historicoEmprestimoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("HistoricoEmprestimo salvo com sucesso");
    }

    @PutMapping
    public ResponseEntity<String> update(@RequestBody HistoricoEmprestimoDTO historicoEmprestimoDTO) {
        try {
            service.update(historicoEmprestimoDTO);
            return ResponseEntity.status(HttpStatus.OK).body("Atualização realizada com sucesso!");
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body("Erro ao atualizar HistoricoEmprestimo: " + ex.getMessage());
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        try {
            service.delete(id);
            return ResponseEntity.status(HttpStatus.OK).body("HistoricoEmprestimo excluído com sucesso!");
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body("Erro ao excluir HistoricoEmprestimo: " + ex.getMessage());
        }
    }

    @ExceptionHandler(AbstractMinhaException.class)
    public ResponseEntity<ExceptionResponse> handleAbstractMinhaException(AbstractMinhaException ex, HttpServletRequest request) throws IOException {
        ExceptionResponse response = new ExceptionResponse(ex, request.getRequestURI());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}
