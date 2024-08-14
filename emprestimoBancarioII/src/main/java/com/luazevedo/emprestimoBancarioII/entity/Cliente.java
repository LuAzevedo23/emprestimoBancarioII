package com.luazevedo.emprestimoBancarioII.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entidade que representa um cliente no sistema de empréstimos bancários.
 * Um cliente pode solicitar empréstimos, que serão vinculados a esta entidade.
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

    private String endereco;
    private String telefone;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = true)
    private String senha;

    @Column(nullable = false)
    private String cpf;
}



