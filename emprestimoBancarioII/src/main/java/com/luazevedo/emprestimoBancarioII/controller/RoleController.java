package com.luazevedo.emprestimoBancarioII.controller;

import com.luazevedo.emprestimoBancarioII.entity.Role;
import com.luazevedo.emprestimoBancarioII.exception.AbstractMinhaException;
import com.luazevedo.emprestimoBancarioII.exception.RoleNotFoundException;
import com.luazevedo.emprestimoBancarioII.json.response.ExceptionResponse;
import com.luazevedo.emprestimoBancarioII.repository.RoleRepository;
import com.luazevedo.emprestimoBancarioII.service.RoleService;
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
@RequestMapping("/api/roles")
@Api(value = "RoleController", tags = "Roles")
public class RoleController {

    @Autowired
    RoleService service;
    @Autowired
    private RoleRepository repository;

    @ApiOperation(value = "Retorna todos os roles", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Roles retornados com sucesso"),
            @ApiResponse(code = 401, message = "Você não está autorizado a ver este recurso"),
            @ApiResponse(code = 403, message = "Acesso ao recurso proibido"),
            @ApiResponse(code = 404, message = "Recurso não encontrado"),
            @ApiResponse(code = 422, message = "Dados de requisição inválida"),
            @ApiResponse(code = 500, message = "Erro ao realizar busca dos dados")
    })

    @GetMapping
    public List<Role> findAll() {
        return repository.findAll();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<String> save(@RequestBody Role role) {
        service.save(role);
        return ResponseEntity.status(HttpStatus.CREATED).body("Role salvo com sucesso");
    }

    @PutMapping("/{id}")
    public Role update(@PathVariable Long id, @RequestBody Role role) {
        // Chama o serviço para atualizar o role passando o ID e o objeto Role
        return service.update(id, role);
    }


    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        try {
            service.delete(id);
            return ResponseEntity.status(HttpStatus.OK).body("Role excluído com sucesso!");
        }catch (RoleNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Role não encontrado: " + ex.getMessage());
        } catch (Exception ex) {
     return ResponseEntity.internalServerError().body("Erro ao excluir Role: " + ex.getMessage());
        }
    }

    @ExceptionHandler(AbstractMinhaException.class)
    public ResponseEntity<ExceptionResponse> handleAbstractMinhaException(AbstractMinhaException ex, HttpServletRequest request) throws IOException {
        ExceptionResponse response = new ExceptionResponse(ex, request.getRequestURI());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}
