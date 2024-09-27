package com.luazevedo.emprestimoBancarioII.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
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
@Schema(description = "Entidade que representa um papel (role) atribuído a um usuário no sistema.")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "O ID único do role", example = "1", required = true)
    private Long id;

    @Column(nullable = false, unique = true)
    @Schema(description = "Nome do papel (role) atribuído ao usuário.", example = "ROLE_USER", required = true)
    private String nome;

    @ManyToMany(mappedBy = "roles")
    private Set<Usuario> usuarios = new HashSet<>();

    /**
     * COnstrutor para criar um papel com o nome especificado.
     *
     * @param nome     o nome do papel
     */

    public Role(String nome){
        this.nome = nome;
    }
    public void addUsuario(Usuario usuario) {
        this.usuarios.add(usuario);
        usuario.getRoles().add(this);
    }

    public void removeUsuario(Usuario usuario) {
        this.usuarios.remove(usuario);
        usuario.getRoles().remove(this);
    }
}