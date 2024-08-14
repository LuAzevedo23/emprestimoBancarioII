package com.luazevedo.emprestimoBancarioII.exception;

/**
 * Exceção lançada quando um empréstimo não é encontrado.
 * <p>
 * Esta exceção é utilizada para indicar que o empréstimo com o ID especificado não foi encontrado no banco de dados.
 * </p>
 */
public class EmprestimoNotFoundException extends RuntimeException {

    /**
     * Construtor com mensagem personalizada.
     *
     * @param message A mensagem de erro.
     */
    public EmprestimoNotFoundException(String message) {
        super(message);
    }

    /**
     * Construtor com mensagem personalizada e causa.
     *
     * @param message A mensagem de erro.
     * @param cause A causa da exceção.
     */
    public EmprestimoNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
