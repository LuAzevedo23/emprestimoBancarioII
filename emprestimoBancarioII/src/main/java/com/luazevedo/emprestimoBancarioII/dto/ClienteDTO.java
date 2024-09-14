package com.luazevedo.emprestimoBancarioII.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDTO {

    @JsonProperty
    @NotNull(message = "O ID não pode ser nulo")
    Long id;

    @NotBlank(message = "O nome não pode ser vazio")
    String nome;

    @NotBlank(message = "O email não pode ser vazio")
    String email;

    @NotBlank(message = "O telefone não pode ser vazio")
    String telefone;

    @NotBlank(message = "O endereco não pode ser vazio")
    String endereco;

    @NotBlank(message = "A senha não pode ser vazia")
    String senha;

    @NotBlank(message = "O cpf não pode ser vazio")
    String cpf;

    @JsonProperty
    private List<EmprestimoDTO> emprestimos;


}
