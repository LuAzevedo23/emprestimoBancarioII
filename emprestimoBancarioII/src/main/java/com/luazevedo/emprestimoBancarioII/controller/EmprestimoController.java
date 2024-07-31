package com.luazevedo.emprestimoBancarioII.controller;

import com.luazevedo.emprestimoBancarioII.dto.EmprestimoDTO;
import com.luazevedo.emprestimoBancarioII.entity.Emprestimo;
import com.luazevedo.emprestimoBancarioII.exception.AbstractMinhaException;
import com.luazevedo.emprestimoBancarioII.exception.ExceptionResponse;
import com.luazevedo.emprestimoBancarioII.mapper.EmprestimoMapper;
import com.luazevedo.emprestimoBancarioII.repository.EmprestimoRepository;
import com.luazevedo.emprestimoBancarioII.service.EmprestimoService;
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
@RequestMapping("/api/emprestimo")
public class EmprestimoController {

    @Autowired
    EmprestimoService service;
    @Autowired
    private EmprestimoRepository repository;
    @Autowired
    private EmprestimoMapper mapper;

    @GetMapping
    public List<EmprestimoDTO> findAll() {
        List<Emprestimo> emprestimos = repository.findAll();
        return mapper.paraDTO(emprestimos);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<EmprestimoDTO> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<String> save(@RequestBody EmprestimoDTO emprestimoDTO) {
        service.save(emprestimoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Emprestimo salvo com sucesso");
    }

    @PutMapping
    public ResponseEntity<String> update(@RequestBody EmprestimoDTO emprestimoDTO) {
        try {
            service.update(emprestimoDTO);
            return ResponseEntity.status(HttpStatus.OK).body("Atualização realizada com sucesso!");
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body("Erro ao atualizar Emprestimo: " + ex.getMessage());
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        try {
            service.delete(id);
            return ResponseEntity.status(HttpStatus.OK).body("Emprestimo excluído com sucesso!");
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body("Erro ao excluir Emprestimo: " + ex.getMessage());
        }
    }

    @ExceptionHandler(AbstractMinhaException.class)
    public ResponseEntity<ExceptionResponse> handleAbstractMinhaException(AbstractMinhaException ex, HttpServletRequest request) throws IOException {
        ExceptionResponse response = new ExceptionResponse(ex.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}
