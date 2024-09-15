package com.luazevedo.emprestimoBancarioII.usuario;

import com.luazevedo.emprestimoBancarioII.entity.Usuario;
import com.luazevedo.emprestimoBancarioII.repository.UsuarioRepository;
import com.luazevedo.emprestimoBancarioII.service.UsuarioService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Classe de teste para o serviço de Usuário.
 * Utiliza Mockito para simular as dependências.
 */
@ExtendWith(MockitoExtension.class)
public class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    /**
     * Testa o método de salvar um usuáro.
     * Verifica se o método do repositório foi chamado corretamente.
     */

    @Test
    public void testSalvarUsuario(){
        //Cria um objeto usuário de exemplo
        Usuario usuario = new Usuario();
        usuario.setUsername("luazevedo");
        usuario.setSenha("password");

        //Defina o comportamento do mock
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);

        //Executa o método de salvar usuário
        Usuario resultado = usuarioService.salvar(usuario);

        //Verifica o resultado
        assertEquals("luazevedo", resultado.getUsername());
        assertEquals("password", resultado.getSenha());
    }
}
