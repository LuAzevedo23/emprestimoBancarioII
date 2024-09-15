package com.luazevedo.emprestimoBancarioII.advice;

import com.luazevedo.emprestimoBancarioII.controller.advice.GlobalExceptionHandle;
import com.luazevedo.emprestimoBancarioII.exception.AbstractMinhaException;
import com.luazevedo.emprestimoBancarioII.exception.RoleNotFoundException;
import com.luazevedo.emprestimoBancarioII.json.response.ExceptionResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GlobalExceptionHandlerTest {

    private GlobalExceptionHandle handle =  new GlobalExceptionHandle();

    @Test
    public void testHandleRoleNotFoundException(){
        RoleNotFoundException exception = new RoleNotFoundException("Role não encontrada");
        ResponseEntity<String> response = handle.handleRoleNotFoundException(exception);

        assertEquals(404, response.getStatusCodeValue());
        assertEquals("Role não encontrada", response.getBody());
    }
    @Test
    public void testHandleAbstractMinhaException(){
        AbstractMinhaException exception = new AbstractMinhaException("Erro interno");
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getRequestURI()).thenReturn("/api/exemplo");

        ResponseEntity<ExceptionResponse> response = handle.handleAbstractException(exception, request);

        assertEquals(500, response.getStatusCodeValue());
        assertEquals("Erro interno", response.getBody().getError());
        assertEquals("/api/exemplo", response.getBody().getUrl());
    }
}
