package com.luazevedo.emprestimoBancarioII.json.response;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    Instant dataHora;

    /**
     * Contrutor para criar uma instância de ExceptionResponse.
     *
     * @param ex Exceção que gerou a resposta.
     * @param url URL onde ocorreu o erro.
     */
    public ExceptionResponse(Exception ex, String url) {
        error = ex.getMessage();
        dataHora = Instant.now();
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
        this.dataHora = Instant.now();
    }

}