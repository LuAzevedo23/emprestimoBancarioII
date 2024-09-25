package com.luazevedo.emprestimoBancarioII.controller;

import com.luazevedo.emprestimoBancarioII.dto.SimulacaoRequestDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/simulacao")
@Tag(name = "Simulação", description = "API para simulação de empréstimos")
public class SimulacaoController {

    @PostMapping("/calcular")
    @Operation(description = "Calcular simulação de empréstimo", responses = {
            @ApiResponse(responseCode = "200", description = "Simulação calculada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados de entrada inválidos")
    })
    public ResponseEntity<String> calcularSimulacao(@RequestBody @Valid SimulacaoRequestDTO request) {
        // Validação dos parâmetros de entrada
        if (request.getValor() <= 0 || request.getParcelas() <= 0) {
            return ResponseEntity.badRequest().body("Valor e parcelas devem ser maiores que zero.");
        }

        // Lógica para cálculo de simulação
        double taxaJuros = 0.02; // Exemplo de taxa de juros
        double valorFinal = request.getValor() * Math.pow(1 + taxaJuros, request.getParcelas());

        // Formatar o resultado para duas casas decimais
        String resultado = String.format("O valor final da simulação é: R$ %.2f", valorFinal);
        return ResponseEntity.ok(resultado);
    }
}
