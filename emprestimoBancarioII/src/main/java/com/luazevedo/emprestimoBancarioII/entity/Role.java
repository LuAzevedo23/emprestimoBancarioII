package com.luazevedo.emprestimoBancarioII.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

/**
 * Entidade que representa um papel ou permissão dentro do sistema.
 * Um papel pode ser atribuído a múltiplos usuários, e um usuário pode ter múltiplos papéis.
 *
 * @author Luciene Azevedo
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nome;

    @ManyToMany(mappedBy = "roles")
    private Set<Usuario> usuarios;

    /**
     * COnstrutor para criar um papel com o nome especificado.
     *
     * @param nome     o nome do papel
     */

    public Role(String nome){
        this.nome = nome;


    }
}