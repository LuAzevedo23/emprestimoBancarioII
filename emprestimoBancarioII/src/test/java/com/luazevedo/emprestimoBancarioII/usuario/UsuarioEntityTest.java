package com.luazevedo.emprestimoBancarioII.usuario;

import com.luazevedo.emprestimoBancarioII.entity.Usuario;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UsuarioEntityTest {

    @Test
    public void testUsuarioEntity(){
        //Criação da entidade
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setUsername("luAzevedo");
        usuario.setSenha("senhaSecreta");

        //Verificação dos valores
        assertEquals(1L, usuario.getId());
        assertEquals("luAzevedo", usuario.getUsername());
        assertEquals("senhaSecreta", usuario.getSenha());
    }
}
