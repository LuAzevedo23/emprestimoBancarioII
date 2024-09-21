package com.luazevedo.emprestimoBancarioII.entity;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Table(name = "historico_emprestimos")
@Schema(description = "Representa o histórico de empréstimos do cliente.")
public class HistoricoEmprestimo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único do histórico de empréstimo.", example = "1", required = true)
    private Long id;

    @Column(nullable = false)
    @Schema(description = "Data em que o evento do empréstimo ocorreu.", example = "2024-09-19", required = true)
    private Instant dataEvento;

    @Column(nullable = false)
    @Schema(description = "Descrição do histórico do empréstimo.", required = true)
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "emprestimo_id", nullable = false)
    @Schema(description = "Empréstimo associado ao histórico.")
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
