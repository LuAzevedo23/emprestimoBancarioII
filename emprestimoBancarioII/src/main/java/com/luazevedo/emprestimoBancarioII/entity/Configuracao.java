package com.luazevedo.emprestimoBancarioII.entity;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description =  "Entidade configuracao que representa uma chave e seu valor no sistema")
public class Configuracao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "O ID único do configuracao", example = "1", required = true)
    private Long id;

    @Column(nullable = false)
    @Schema(description = "Chave da configuracao", example = "email.smtp", required = true)
    private String chave;

    @Column(nullable = false)
    @Schema(description = "Valor da configuração", example = "smtp.gmail.com", required = true)
    private String valor;

    @Column(nullable = false)
    @Schema(description = "Tipo de valor da configuração", example = "String", required = true)
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
