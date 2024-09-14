package com.luazevedo.emprestimoBancarioII.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entidade que representa uma configuração chave-valor do sistema.
 * Pode ser usada para armazenar parâmetros configuráveis da aplicação.
 *
 * <p>
 * Tipos de valores podem ser armazenados com diferentes tipos, como String, Integer, etc.
 * </p>
 *
 * @author Luciene Azevedo
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "configuracao")
public class Configuracao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String chave;

    @Column(nullable = false)
    private String valor;

    @Column(nullable = false)
    private String tipoValor;

    public Configuracao(String chave, String valor, String tipoValor){
        this.chave = chave;
        this.valor = valor;
        this.tipoValor = tipoValor;

    }
    public Long getId() {
        return id;
    }
    public void setId(Long id){
        this.id = id;
    }
}
