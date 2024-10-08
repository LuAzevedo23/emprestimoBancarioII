package com.luazevedo.emprestimoBancarioII.controller;

import com.luazevedo.emprestimoBancarioII.dto.ConfiguracaoDTO;
import com.luazevedo.emprestimoBancarioII.entity.Configuracao;
import com.luazevedo.emprestimoBancarioII.exception.AbstractMinhaException;
import com.luazevedo.emprestimoBancarioII.json.response.ExceptionResponse;
import com.luazevedo.emprestimoBancarioII.mapper.ConfiguracaoMapper;
import com.luazevedo.emprestimoBancarioII.repository.ConfiguracaoRepository;
import com.luazevedo.emprestimoBancarioII.service.ConfiguracaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
@RequestMapping("/api/configuracoes")
//@Api(value = "ConfiguracaoController", tags = "Configuracoes")
public class ConfiguracaoController {

    @Autowired
    ConfiguracaoService service;
    @Autowired
    private ConfiguracaoRepository repository;
    @Autowired
    private ConfiguracaoMapper mapper;

    @Operation(description = "Retorna todos as configuracoes", responses = {
            @ApiResponse(responseCode = "200", description = "Configurações retornadas com sucesso"),
            @ApiResponse(responseCode = "401", description = "Você não está autorizado a ver este recurso"),
            @ApiResponse(responseCode = "403", description = "Acesso ao recurso proibido"),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar busca dos dados")
    })
    @GetMapping
    public List<ConfiguracaoDTO> findAll() {
        List<Configuracao> configuracoes = repository.findAll();
        return mapper.paraDTO(configuracoes);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ConfiguracaoDTO> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<String> save(@RequestBody ConfiguracaoDTO configuracaoDTO) {
        service.save(configuracaoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Configuracao salvo com sucesso");
    }

    @PutMapping
    public ResponseEntity<String> update(@RequestBody ConfiguracaoDTO configuracaoDTO) {
        try {
            service.update(configuracaoDTO);
            return ResponseEntity.status(HttpStatus.OK).body("Atualização realizada com sucesso!");
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body("Erro ao atualizar Configuracao: " + ex.getMessage());
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        try {
            service.delete(id);
            return ResponseEntity.status(HttpStatus.OK).body("Configuracao excluído com sucesso!");
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body("Erro ao excluir Configuracao: " + ex.getMessage());
        }
    }

    @ExceptionHandler(AbstractMinhaException.class)
    public ResponseEntity<ExceptionResponse> handleAbstractMinhaException(AbstractMinhaException ex, HttpServletRequest request) throws IOException {
        ExceptionResponse response = new ExceptionResponse(ex, request.getRequestURI());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}

