package com.luazevedo.emprestimoBancarioII.controller.advice;

import com.luazevedo.emprestimoBancarioII.exception.AbstractMinhaException;
import com.luazevedo.emprestimoBancarioII.json.response.ExceptionResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;

/**
 * Classe para lidar com exceções globais na aplicação.
 */
@ControllerAdvice
public class GlobalExceptionHandle {

    /**
     * Lida com exceções do tipo AbstractMinhaException.
     *
     * @param ex      Exceção lançada.
     * @param request Solicitação HTTP.
     * @return ResponseEntity com informações da exceção.
     * @throws IOException Caso ocorra erro ao processar a exceção.
     */

    @ExceptionHandler(AbstractMinhaException.class)
    public ResponseEntity<ExceptionResponse> handleAbstractMinhaException(AbstractMinhaException ex, HttpServletRequest request) throws IOException {
        ExceptionResponse response = new ExceptionResponse(ex, request.getRequestURI());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}

