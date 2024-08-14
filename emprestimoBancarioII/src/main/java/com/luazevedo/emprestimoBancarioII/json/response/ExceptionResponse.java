package com.luazevedo.emprestimoBancarioII.json.response;

import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.time.Instant;
import java.util.Calendar;

/**
 * Classe para representar a resposta de exceção para as APIs.
 */
@Getter
@FieldDefaults(makeFinal = true)
public class ExceptionResponse {

    String error;
    String url;
    Instant dataHora;

    /**
     * Construtor para criar uma instância de ExceptionResponse.
     *
     * @param ex   Exceção que gerou a resposta.
     * @param url  URL onde ocorreu o erro.
     */
    public ExceptionResponse(Exception ex, String url) {
        error = ex.getMessage();
        dataHora = Calendar.getInstance().toInstant();
        this.url = url;
    }

    /**
     * Construtor para criar uma instância de ExceptionResponse com mensagem e URI.
     *
     * @param message Mensagem do erro.
     * @param uri     URI onde ocorreu o erro.
     */
    public ExceptionResponse(String message, String uri) {
        this.error = message;
        this.url = uri;
        this.dataHora = Calendar.getInstance().toInstant();
    }

}