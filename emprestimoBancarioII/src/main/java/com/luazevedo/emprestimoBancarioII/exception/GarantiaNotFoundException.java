package com.luazevedo.emprestimoBancarioII.exception;

/**
 * Exceção lançada quando uma garantia não é encontrada.
 */
public class GarantiaNotFoundException extends RuntimeException {

    public GarantiaNotFoundException(String message) {

        super(message);
    }
}
