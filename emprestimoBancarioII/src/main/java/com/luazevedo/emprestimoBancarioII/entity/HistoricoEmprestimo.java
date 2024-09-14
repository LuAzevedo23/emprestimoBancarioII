package com.luazevedo.emprestimoBancarioII.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

/**
 * Entidade que representa o histórico de eventos de um empréstimo.
 * Armazena informações sobre eventos relevantes ocorridos durante a vigência de um empréstimo.
 *
 * @author Luciene Azevedo
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "historico_emprestimo")
public class HistoricoEmprestimo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Instant dataEvento;

    @Column(nullable = false)
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "emprestimo_id", nullable = false)
    private Emprestimo emprestimo;

    /**
     * Contrutor para criar um histórico de evento associado a um empréstimo
     *
     * @param dataEvento    a data do evento
     * @param descricao     a descricao do evento
     * @param emprestimo    o emprestimo associado
     * */

    public HistoricoEmprestimo(Instant dataEvento, String descricao, Emprestimo emprestimo){
        this.dataEvento = dataEvento;
        this.descricao = descricao;
        this.emprestimo = emprestimo;
    }
}
