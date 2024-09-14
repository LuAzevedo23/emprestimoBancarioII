package com.luazevedo.emprestimoBancarioII.entity;

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
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = true)
    private String endereco;

    @Column(nullable = true)
    private String telefone;

    @Email
    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = true)
    private String senha;

    @Column(nullable = false, unique = true)
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
    private List<Emprestimo> emprestimos;
}
    /*
    @PrePersist
    @PreUpdate
    public void encryptPassword() {
        if (this.senha != null) {
            this.senha = PasswordEncoder.encode(this.senha); // Supondo que tenha uma classe PasswordEncoder
        }
    }
    */




