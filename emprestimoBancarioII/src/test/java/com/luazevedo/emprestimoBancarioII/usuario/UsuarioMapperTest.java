package com.luazevedo.emprestimoBancarioII.usuario;

import com.luazevedo.emprestimoBancarioII.dto.UsuarioDTO;
import com.luazevedo.emprestimoBancarioII.entity.Role;
import com.luazevedo.emprestimoBancarioII.entity.Usuario;
import com.luazevedo.emprestimoBancarioII.mapper.UsuarioMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class UsuarioMapperTest {

    @Autowired
    private UsuarioMapper usuarioMapper;

    @Test
    public void testUsuarioToUsuarioDTO(){
        Usuario usuario = new Usuario();
        usuario.setUsername("test_user");
        usuario.setId(1L);

        //Criar um role e associá-la ao usuário
        Role role = new Role();
        role.setNome("ADMIN");
        usuario.setRoles(Collections.singleton(role));

        //Converter para DTO
        UsuarioDTO usuarioDTO = usuarioMapper.paraDTO(usuario);


        //Verificações
        assertEquals(usuario.getId(), usuarioDTO.getId());
        assertEquals(usuario.getUsername(), usuarioDTO.getUsername());

        //Verificar se a lista de roles foi mapeada corretamente
        assertEquals(1, usuarioDTO.getRoles().size());
        assertEquals("ADMIN", usuarioDTO.getRoles().get(0).getNome());
    }
}
