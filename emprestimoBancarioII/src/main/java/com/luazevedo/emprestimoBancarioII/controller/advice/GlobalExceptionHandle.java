package com.luazevedo.emprestimoBancarioII.controller.advice;

import com.luazevedo.emprestimoBancarioII.exception.AbstractMinhaException;
import com.luazevedo.emprestimoBancarioII.exception.RoleNotFoundException;
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
     */

    public ResponseEntity<String> handleRoleNotFoundException(RoleNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

    public ResponseEntity<ExceptionResponse> handleAbstractException(AbstractMinhaException ex, HttpServletRequest request){
        ExceptionResponse response = new ExceptionResponse(ex, request.getRequestURI());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

}

