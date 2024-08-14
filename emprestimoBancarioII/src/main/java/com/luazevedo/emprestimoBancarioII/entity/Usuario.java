package com.luazevedo.emprestimoBancarioII.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

/**
 * Entidade que representa um usuário do sistema.
 * Um usuário pode ter múltiplos papéis, que determinam suas permissões dentro do sistema.
 *
 * @author Luciene Azevedo
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String senha;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "usuario_role",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> role;
}


/*
COMO AS ANOTATIONS FUNCIONAM AQUI.
@Id e @GeneratedValue: Define o id como a chave primária da entidade e configura a geração automática de valores.
@Column: Mapeia os campos username e senha para as colunas na tabela tb_usuarios.
@ManyToMany: Configura um relacionamento muitos-para-muitos com a entidade Role.
@JoinTable: Define a tabela de junção tb_usuarios_roles, que conecta a tabela tb_usuarios
 com a tabela tb_roles. As colunas usuario_id e role_id são usadas para armazenar os IDs dos usuários e roles,
 respectivamente.
 */