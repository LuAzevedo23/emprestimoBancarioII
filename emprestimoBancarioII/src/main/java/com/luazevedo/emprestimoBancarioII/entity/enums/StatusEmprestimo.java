package com.luazevedo.emprestimoBancarioII.entity.enums;

import lombok.ToString;

/**
 * Enum que representa os possíveis status de um empréstimo.
 * Os status indicam o estado atual do empréstimo no sistema.
 *
 * @author Luciene Azevedo
 */

@ToString
public enum StatusEmprestimo {
    PENDENTE,
    APROVADO,
    REJEITADO,
    PAGO
}
