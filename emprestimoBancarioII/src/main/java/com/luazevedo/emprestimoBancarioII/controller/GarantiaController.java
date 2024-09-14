package com.luazevedo.emprestimoBancarioII.controller;

import com.luazevedo.emprestimoBancarioII.dto.GarantiaDTO;
import com.luazevedo.emprestimoBancarioII.entity.Garantia;
import com.luazevedo.emprestimoBancarioII.exception.AbstractMinhaException;
import com.luazevedo.emprestimoBancarioII.exception.GarantiaNotFoundException;
import com.luazevedo.emprestimoBancarioII.json.response.ExceptionResponse;
import com.luazevedo.emprestimoBancarioII.mapper.GarantiaMapper;
import com.luazevedo.emprestimoBancarioII.service.GarantiaService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

/**
 * Controller responsável por operações relacionadas a Garantia.
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/garantias")
public class GarantiaController {

    private final GarantiaMapper garantiaMapper;
    private final GarantiaService garantiaService;

    /**
     * Retorna todas as garantias.
     *
     * @return Lista de GarantiaDTO
     */
    @ApiOperation(value = "Retorna todas as garantias", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Garantias retornadas com sucesso"),
            @ApiResponse(code = 401, message = "Você não está autorizado a ver este recurso"),
            @ApiResponse(code = 403, message = "Acesso ao recurso proibido"),
            @ApiResponse(code = 404, message = "Recurso não encontrado"),
            @ApiResponse(code = 422, message = "Dados de requisição inválida"),
            @ApiResponse(code = 500, message = "Erro ao realizar busca dos dados")
    })
    @GetMapping
    public List<GarantiaDTO> findAll() {
        return garantiaService.findAll();
    }

    /**
     * Retorna uma garantia pelo ID.
     *
     * @param id ID da garantia
     * @return GarantiaDTO correspondente
     */
    @GetMapping(value = "/{id}")
    public ResponseEntity<GarantiaDTO> findById(@PathVariable("id") Long id) {
        GarantiaDTO garantiaDTO = garantiaService.findById(id);
        return ResponseEntity.ok(garantiaDTO);
    }

    /**
     * Salva uma nova garantia.
     *
     * @param garantiaDTO DTO da garantia a ser salva
     * @return Mensagem de sucesso
     */
    @PostMapping
    public ResponseEntity<String> save(@RequestBody GarantiaDTO garantiaDTO) {
        garantiaService.save(garantiaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Garantia salva com sucesso");
    }

    /**
     * Atualiza uma garantia existente.
     *
     * @param garantiaDTO DTO da garantia a ser atualizada
     * @return Mensagem de sucesso
     */
    @PutMapping ("/{id}")
    public ResponseEntity<GarantiaDTO> update(@PathVariable Long id, @RequestBody GarantiaDTO garantiaDTO) {
        try {
            GarantiaDTO updatedGarantia = garantiaService.update(id,garantiaDTO);
            return ResponseEntity.ok(updatedGarantia); //Retorna o DTO atualizado.
        } catch (GarantiaNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); //Retorna 404 se a Garantia não for encontrada
        } catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null); //Retorna 500 em caso de erro
        }
    }

    /**
     * Exclui uma garantia pelo ID.
     *
     * @param id ID da garantia a ser excluída
     * @return Mensagem de sucesso
     */
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        try {
            garantiaService.delete(id);
            return ResponseEntity.ok("Garantia excluída com sucesso!");
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body("Erro ao excluir Garantia: " + ex.getMessage());
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