package com.luazevedo.emprestimoBancarioII.entity.enums;

import lombok.ToString;

/**
 * Enum que representa os possíveis status de um pagamento.
 * Os status indicam o estado atual do pagamento no sistema.
 *
 * @author Luciene Azevedo
 */

@ToString
public enum StatusPagamento {
    PENDENTE,
    PAGO,
    ATRASADO
}
