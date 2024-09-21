package com.luazevedo.emprestimoBancarioII.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "DTO que representa os dados de um cliente")
public class ClienteDTO {

    @JsonProperty
    @NotNull(message = "O ID não pode ser nulo")
    @Schema(description = "ID único do cliente", example = "1", required = true)
    Long id;

    @NotBlank(message = "O nome não pode ser vazio")
    @Schema(description = "Nome completo do cliente", example = "João da Silva", required = true )
    String nome;

    @NotBlank(message = "O email não pode ser vazio")
    @Schema(description = "E-mail do cliente", example = "joao.silva@gmail.com", required = true)
    String email;

    @NotBlank(message = "O telefone não pode ser vazio")
    @Schema(description = "Telefone do cliente", example = "(11) 98765-4321")
    String telefone;

    @NotBlank(message = "O endereco não pode ser vazio")
    @Schema(description = "Endereço do cliente", example = "Rua das Flores, 123")
    String endereco;

    @NotBlank(message = "A senha não pode ser vazia")
    @Schema(description = "Senha do cliente", example = "senha123")
    String senha;

    @NotBlank(message = "O cpf não pode ser vazio")
    @Schema(description = "CPF do cliente", example = "123.456.789-00", required = true)
    String cpf;

    @JsonProperty
    @Schema(description = "Lista de empréstimos associados ao cliente")
    private List<EmprestimoDTO> emprestimos;


}
