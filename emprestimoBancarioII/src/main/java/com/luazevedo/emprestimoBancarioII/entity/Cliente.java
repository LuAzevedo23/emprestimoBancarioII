package com.luazevedo.emprestimoBancarioII.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Entidade que representa um cliente no sistema de empréstimos bancários.
 * Um cliente pode solicitar empréstimos, que serão vinculados a esta entidade.
 *
 * <p>
 * A senha do cliente será criptografada antes de ser persistida no banco de dados.
 * </p>
 *
 * @author Luciene Azevedo
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cliente")
@Schema(description = "Entidade Cliente que representa os dados de um cliente no sistema")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "O ID único do cliente", example = "1", required = true)
    private Long id;

    @Column(nullable = false)
    @Schema(description = "Nome completo do cliente", example = "João da Silva", required = true)
    private String nome;

    @Column(nullable = true)
    @Schema(description = "Endereço do cliente", example = "Rua das Flores, 123")
    private String endereco;

    @Column(nullable = true)
    @Schema(description = "Telefone do cliente", example = "(11) 98765-4321")
    private String telefone;

    @Email
    @Column(nullable = false, unique = true)
    @Schema(description = "E-mail do cliente", example = "joao.silva@gmail.com", required = true)
    private String email;

    @Column(nullable = true)
    @Schema(description = "Senha do cliente", example = "senha123")
    private String senha;

    @Column(nullable = false, unique = true)
    @Schema(description = "CPF do cliente", example = "123.456.789-00", required = true)
    private String cpf;

    public Cliente (String nome, String email, String cpf) {
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
    }
        public Long getId() {
            return id;
        }
        public void setId(Long id){
        this.id = id;
        }

    @OneToMany(mappedBy = "cliente")
    @Schema(description = "Lista de empréstimos associados ao cliente")
    private List<Emprestimo> emprestimos;
}



