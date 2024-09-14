package com.luazevedo.emprestimoBancarioII.exception;

/**
 * Exceção lançada quando um usuário não é encontrada.
 */
public class UsuarioNotFoundException extends RuntimeException {

    public UsuarioNotFoundException(String message) {

        super(message);
    }
}
