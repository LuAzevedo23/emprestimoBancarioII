package com.luazevedo.emprestimoBancarioII.controller;

import com.luazevedo.emprestimoBancarioII.dto.UsuarioCreateDTO;
import com.luazevedo.emprestimoBancarioII.dto.UsuarioDTO;
import com.luazevedo.emprestimoBancarioII.entity.Usuario;
import com.luazevedo.emprestimoBancarioII.exception.AbstractMinhaException;
import com.luazevedo.emprestimoBancarioII.json.response.ExceptionResponse;
import com.luazevedo.emprestimoBancarioII.mapper.UsuarioMapper;
import com.luazevedo.emprestimoBancarioII.repository.UsuarioRepository;
import com.luazevedo.emprestimoBancarioII.service.UsuarioService;
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
@RequestMapping("/api/usuarios")
//@Api(value = "UsuarioController", tags = "Usuarios")
public class UsuarioController {

    @Autowired
    UsuarioService service;
    @Autowired
    private UsuarioRepository repository;
    @Autowired
    private UsuarioMapper mapper;

    @Operation(description = "Retorna todos os usuários", responses = {
            @ApiResponse(responseCode = "200", description = "Usuários retornados com sucesso"),
            @ApiResponse(responseCode = "401", description = "Você não está autorizado a ver este recurso"),
            @ApiResponse(responseCode = "403", description = "Acesso ao recurso proibido"),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar busca dos dados")
    })

    @GetMapping
    public List<UsuarioDTO> findAll() {
        List<Usuario> usuarios = repository.findAll();
        return mapper.paraDTO(usuarios);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UsuarioDTO> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<UsuarioDTO> save(@RequestBody UsuarioCreateDTO usuarioCreateDTO) {
        UsuarioDTO savedUsuario = service.save(usuarioCreateDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUsuario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDTO> update(@PathVariable Long id, @RequestBody UsuarioDTO usuarioDTO) {
        UsuarioDTO updatedUsuario = service. update(id, usuarioDTO);
        return ResponseEntity.ok(updatedUsuario);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        try {
            service.delete(id);
            return ResponseEntity.status(HttpStatus.OK).body("Usuário excluído com sucesso!");
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body("Erro ao excluir Usuário: " + ex.getMessage());
        }
    }

    @ExceptionHandler(AbstractMinhaException.class)
    public ResponseEntity<ExceptionResponse> handleAbstractMinhaException(AbstractMinhaException ex, HttpServletRequest request) throws IOException {
        ExceptionResponse response = new ExceptionResponse(ex, request.getRequestURI());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }


}
