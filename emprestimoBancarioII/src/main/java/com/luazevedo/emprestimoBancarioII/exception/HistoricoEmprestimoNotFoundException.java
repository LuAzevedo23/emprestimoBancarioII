package com.luazevedo.emprestimoBancarioII.exception;

/**
 * Exceção lançada quando um histórico não é encontrada.
 */
public class HistoricoEmprestimoNotFoundException extends RuntimeException {

    public HistoricoEmprestimoNotFoundException(String message) {

        super(message);
    }
}
