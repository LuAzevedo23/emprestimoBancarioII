package com.luazevedo.emprestimoBancarioII.role;

import com.luazevedo.emprestimoBancarioII.exception.RoleNotFoundException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RoleNotFoundExceptionTest {

    @Test
    public void testRoleNotFoundException(){
        RoleNotFoundException exception = new RoleNotFoundException("Role não encontrada");
        assertEquals("Role não encontrada", exception.getMessage());
    }
}
