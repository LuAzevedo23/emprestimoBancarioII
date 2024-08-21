package com.luazevedo.emprestimoBancarioII.controller;

import com.luazevedo.emprestimoBancarioII.dto.PagamentoDTO;
import com.luazevedo.emprestimoBancarioII.service.PagamentoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador para operações relacionadas a pagamentos.
 */

@RestController
@RequestMapping("/pagamentos")
@Api(value = "PagamentoController", tags = "Pagamentos")
public class PagamentoController {

    @Autowired
    private PagamentoService pagamentoService;

    @ApiOperation(value = "Retorna todos os pagamentos", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Pagamentos retornados com sucesso"),
            @ApiResponse(code = 401, message = "Você não está autorizado a ver este recurso"),
            @ApiResponse(code = 403, message = "Acesso ao recurso proibido"),
            @ApiResponse(code = 404, message = "Recurso não encontrado"),
            @ApiResponse(code = 422, message = "Dados de requisição inválida"),
            @ApiResponse(code = 500, message = "Erro ao realizar busca dos dados")
    })

    /**
     * Calcula o valor total dos pagamentos com base nos dados fornecidos.
     * Utiliza o serviço {@link PagamentoService} para realizar os cálculos.
     *
     * @param pagamentoDTO O DTO do pagamento contendo informações para o cálculo.
     * @return O DTO do pagamento com o valor calculado.
     */
    @PostMapping("/calcular")
    public PagamentoDTO calcularPagamento(@RequestBody PagamentoDTO pagamentoDTO) {
        return pagamentoService.calcularPagamento(pagamentoDTO);
    }

    /**
     * Encontra todos os pagamentos.
     *
     * @return A lista de DTOs dos pagamentos encontrados.
     */
    @GetMapping
    public List<PagamentoDTO> findAll() {
        return pagamentoService.findAll();
    }

    /**
     * Encontra um pagamento pelo ID.
     *
     * @param id O ID do pagamento.
     * @return O DTO do pagamento encontrado.
     */
    @GetMapping("/{id}")
    public PagamentoDTO findById(@PathVariable Long id) {
        return pagamentoService.findById(id);
    }

    /**
     * Salva um novo pagamento.
     *
     * @param pagamentoDTO O DTO do pagamento a ser salvo.
     * @return O ID do pagamento salvo.
     */
    @PostMapping
    public Long save(@RequestBody PagamentoDTO pagamentoDTO) {
        return pagamentoService.save(pagamentoDTO);
    }

    /**
     * Exclui um pagamento pelo ID.
     *
     * @param id O ID do pagamento a ser excluído.
     */
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        pagamentoService.delete(id);
    }

    /**
     * Atualiza um pagamento existente.
     *
     * @param pagamentoDTO O DTO do pagamento com as novas informações.
     * @return O ID do pagamento atualizado.
     */
    @PutMapping
    public Long update(@RequestBody PagamentoDTO pagamentoDTO) {
        return pagamentoService.update(pagamentoDTO);
    }
}