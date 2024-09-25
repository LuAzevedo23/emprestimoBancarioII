package com.luazevedo.emprestimoBancarioII.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SimulacaoRequestDTO {

    @NotNull(message = "O valor é obrigatório")
    private Double valor;

    @NotNull(message = "O número de parcelas é obrigatório")
    private Integer parcelas;
}
