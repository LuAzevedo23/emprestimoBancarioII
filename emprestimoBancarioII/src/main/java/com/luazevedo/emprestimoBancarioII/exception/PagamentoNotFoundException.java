package com.luazevedo.emprestimoBancarioII.exception;

/**
 * Exceção lançada quando um pagamento não é encontrada.
 */
public class PagamentoNotFoundException extends RuntimeException {

    public PagamentoNotFoundException(String message) {

        super(message);
    }
}
