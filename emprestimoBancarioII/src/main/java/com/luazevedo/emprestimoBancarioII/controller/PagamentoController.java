package com.luazevedo.emprestimoBancarioII.controller;

import com.luazevedo.emprestimoBancarioII.dto.PagamentoDTO;
import com.luazevedo.emprestimoBancarioII.entity.Pagamento;
import com.luazevedo.emprestimoBancarioII.exception.AbstractMinhaException;
import com.luazevedo.emprestimoBancarioII.exception.ExceptionResponse;
import com.luazevedo.emprestimoBancarioII.mapper.PagamentoMapper;
import com.luazevedo.emprestimoBancarioII.repository.PagamentoRepository;
import com.luazevedo.emprestimoBancarioII.service.PagamentoService;
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
@RequestMapping("/api/pagamento")
public class PagamentoController {

    @Autowired
    PagamentoService service;
    @Autowired
    private PagamentoRepository repository;
    @Autowired
    private PagamentoMapper mapper;

    @GetMapping
    public List<PagamentoDTO> findAll() {
        List<Pagamento> pagamentos = repository.findAll();
        return mapper.paraDTO(pagamentos);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<PagamentoDTO> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<String> save(@RequestBody PagamentoDTO pagamentoDTO) {
        service.save(pagamentoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Pagamento salvo com sucesso");
    }

    @PutMapping
    public ResponseEntity<String> update(@RequestBody PagamentoDTO pagamentoDTO) {
        try {
            service.update(pagamentoDTO);
            return ResponseEntity.status(HttpStatus.OK).body("Atualização realizada com sucesso!");
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body("Erro ao atualizar Pagamento: " + ex.getMessage());
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        try {
            service.delete(id);
            return ResponseEntity.status(HttpStatus.OK).body("Pagamento excluído com sucesso!");
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body("Erro ao excluir Pagamento: " + ex.getMessage());
        }
    }

    @ExceptionHandler(AbstractMinhaException.class)
    public ResponseEntity<ExceptionResponse> handleAbstractMinhaException(AbstractMinhaException ex, HttpServletRequest request) throws IOException {
        ExceptionResponse response = new ExceptionResponse(ex.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}
