package com.luazevedo.emprestimoBancarioII.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "DTO que representa uma configuração no sistema")
public class ConfiguracaoDTO {

    @JsonProperty
    @NotNull(message = "O ID não pode ser nulo")
    @Schema(description = "ID único da configuração", example = "1", required =true)
    Long id;

    @NotBlank(message = "A chave não pode ser vazia")
    @Schema(description = "Chave da configuração", example = "email.smtp", required = true)
    String chave;

    @NotBlank(message = "O valor não pode ser vazio")
    @Schema(description = "Valor da configuração", example = "smtp.gmail.com", required = true)
    String valor;
}
