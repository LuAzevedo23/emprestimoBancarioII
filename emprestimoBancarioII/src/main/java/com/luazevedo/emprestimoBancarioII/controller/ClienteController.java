package com.luazevedo.emprestimoBancarioII.controller;

import com.luazevedo.emprestimoBancarioII.dto.ClienteDTO;
import com.luazevedo.emprestimoBancarioII.entity.Cliente;
import com.luazevedo.emprestimoBancarioII.exception.AbstractMinhaException;
import com.luazevedo.emprestimoBancarioII.json.response.ExceptionResponse;
import com.luazevedo.emprestimoBancarioII.mapper.ClienteMapper;
import com.luazevedo.emprestimoBancarioII.repository.ClienteRepository;
import com.luazevedo.emprestimoBancarioII.service.ClienteService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

/**
 * Controller responsável por operações relacionadas a Cliente.
 */

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/clientes")
//@Api(value = "ClienteController", tags = "Clientes")
public class ClienteController {

    @Autowired
    ClienteService service;
    @Autowired
    private ClienteRepository repository;
    @Autowired
    private ClienteMapper mapper;


    //@ApiOperation(value = "Retorna todos os clientes", response = List.class)
    //@ApiResponses(value = {
    //        @ApiResponse(code = 200, message = "Clientes retornados com sucesso"),
     //       @ApiResponse(code = 401, message = "Você não está autorizado a ver este recurso"),
     //       @ApiResponse(code = 403, message = "Acesso ao recurso proibido"),
      //      @ApiResponse(code = 404, message = "Recurso não encontrado")
    //})
    /**
     * Retorna todos os clientes.
     *
     * @return Lista de ClienteDTO
     */
    @GetMapping
    public List<ClienteDTO> findAll() {
        List<Cliente> clientes = repository.findAll();
        return mapper.paraDTO(clientes);
    }

    /**
     * Retorna um cliente pelo ID.
     *
     * @param id ID do cliente
     * @return ClienteDTO correspondente
     */
    @GetMapping(value = "/{id}")
    public ResponseEntity<ClienteDTO> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    /**
     * Salva um novo cliente.
     *
     * @param clienteDTO DTO do cliente a ser salvo
     * @return Mensagem de sucesso
     */
    @PostMapping
    public ResponseEntity<String> save(@RequestBody ClienteDTO clienteDTO) {
        service.save(clienteDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Cliente salvo com sucesso");
    }

    /**
     * Atualiza um cliente existente.
     *
     * @param clienteDTO DTO do cliente a ser atualizado
     * @return Mensagem de sucesso
     */
    @PutMapping
    public ResponseEntity<String> update(@RequestBody ClienteDTO clienteDTO) {
        try {
            service.update(clienteDTO);
            return ResponseEntity.status(HttpStatus.OK).body("Atualização realizada com sucesso!");
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body("Erro ao atualizar Cliente: " + ex.getMessage());
        }
    }

    /**
     * Exclui um cliente pelo ID.
     *
     * @param id ID do cliente a ser excluído
     * @return Mensagem de sucesso
     */
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        try {
            service.delete(id);
            return ResponseEntity.status(HttpStatus.OK).body("Cliente excluído com sucesso!");
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body("Erro ao excluir Cliente: " + ex.getMessage());
        }
    }

    /**
     * Manipula exceções do tipo AbstractMinhaException.
     *
     * @param ex Exceção lançada
     * @param request Detalhes da requisição
     * @return Resposta de erro personalizada
     * @throws IOException em caso de erro
     */
    @ExceptionHandler(AbstractMinhaException.class)
    public ResponseEntity<ExceptionResponse> handleAbstractMinhaException(AbstractMinhaException ex, HttpServletRequest request) throws IOException {
        ExceptionResponse response = new ExceptionResponse(ex, request.getRequestURI());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}