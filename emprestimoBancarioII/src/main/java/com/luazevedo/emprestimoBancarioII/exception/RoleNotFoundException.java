package com.luazevedo.emprestimoBancarioII.exception;

/**
 * Exceção lançada quando um role não é encontrada.
 */
public class RoleNotFoundException extends RuntimeException {

    public RoleNotFoundException(String message) {

        super(message);
    }
}
