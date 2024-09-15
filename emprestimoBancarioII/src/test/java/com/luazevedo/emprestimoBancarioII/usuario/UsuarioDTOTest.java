package com.luazevedo.emprestimoBancarioII.usuario;

import com.luazevedo.emprestimoBancarioII.dto.UsuarioDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UsuarioDTOTest {

    @Test
    public void testUsuarioDTO() {
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setUsername("LuAzevedo");

        assertEquals("LuAzevedo", usuarioDTO.getUsername());
    }
}
