package com.luazevedo.emprestimoBancarioII.controller;

import com.luazevedo.emprestimoBancarioII.dto.ClienteDTO;
import com.luazevedo.emprestimoBancarioII.exception.AbstractMinhaException;
import com.luazevedo.emprestimoBancarioII.json.response.ExceptionResponse;
import com.luazevedo.emprestimoBancarioII.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
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

private final ClienteService clienteService;

   @Operation(description = "Retorna todos os clientes", responses = {
        @ApiResponse(responseCode = "200", description = "Clientes retornados com sucesso"),
           @ApiResponse(responseCode = "401", description = "Você não está autorizado a ver este recurso"),
           @ApiResponse(responseCode = "403", description = "Acesso ao recurso proibido"),
           @ApiResponse(responseCode = "404", description = "Recurso não encontrado")
   })

   @GetMapping
   public ResponseEntity<List<ClienteDTO>> findAll() {
       List<ClienteDTO> clientes = clienteService.findAll(); // Método do serviço para buscar todos os clientes
       return ResponseEntity.ok(clientes);
   }

    /**
     * Retorna um cliente pelo ID.
     *
     * @param id ID do cliente
     * @return ClienteDTO correspondente
     */

    @GetMapping(value = "/{id}")
    public ResponseEntity<ClienteDTO> findById(@PathVariable Long id) {
        ClienteDTO clienteDTO = clienteService.findById(id);
        return ResponseEntity.ok(clienteDTO);
    }

    /**
     * Salva um novo cliente.
     *
     * @param clienteDTO Os dados do cliente a ser salvo.
     * @return Resposta com status de sucesso
     */
    @PostMapping
    public ResponseEntity<String> save(@RequestBody ClienteDTO clienteDTO) {
        clienteService.save(clienteDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Cliente salvo com sucesso!");
    }

    /**
     * Atualiza um cliente existente.
     *
     * @param id            O ID do cliente a ser atualizado.
     * @param clienteDTO    O DTO com os novos dados.
     * @return Resposta com status de sucesso.
     */
    @PutMapping(value = "/{id}")
    public ResponseEntity<String> update(@PathVariable Long id, @RequestBody ClienteDTO clienteDTO) {
       try{
           clienteService.update(id, clienteDTO);
           return ResponseEntity.status(HttpStatus.OK).body("Atualização realizada com sucesso!");
       }catch (Exception ex){
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
    public ResponseEntity<String> delete(@PathVariable Long id) {
        clienteService.delete(id);
        return ResponseEntity.ok("Cliente excluído com sucesso!");
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