package com.luazevedo.emprestimoBancarioII.usuario;

import com.luazevedo.emprestimoBancarioII.entity.Usuario;
import com.luazevedo.emprestimoBancarioII.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Classe de teste de integração para o repositório de Usuário.
 * Utiliza o contexto completo do Spring.
 */

@SpringBootTest
public class UsuarioRepositoryTest {

    @Autowired
    private UsuarioRepository usuarioRepository;

    /**
     * Testa a funcionalidade de salvar um usuário no banco de dados.
     * Verifica se o ID é gerado corretamente.
     */
    @Test
    public void testSalvarUsuario(){
        //Cria um novo usuário.
        Usuario usuario = new Usuario();
        usuario.setUsername("luazevedo");
        usuario.setSenha("password");

        //Salva o usuário e obtém o resultado.
        Usuario resultado = usuarioRepository.save(usuario);

        //Verifica se o ID foi gerado e os campos estão corretos.
        assertNotNull(resultado.getId());
        assertEquals("luazevedo", resultado.getUsername());
        assertEquals("password", resultado.getSenha());
    }
}
