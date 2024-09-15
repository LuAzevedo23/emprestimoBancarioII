package com.luazevedo.emprestimoBancarioII.usuario;

import com.luazevedo.emprestimoBancarioII.controller.UsuarioController;
import com.luazevedo.emprestimoBancarioII.dto.UsuarioDTO;
import com.luazevedo.emprestimoBancarioII.service.UsuarioService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UsuarioControllerTest {

    @InjectMocks
    private UsuarioController usuarioController;

    @Mock
    private UsuarioService usuarioService;

    @Test
    public void testSalvarUsuario(){
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setUsername("luAzevedo");

        //Mock do serviço
        when(usuarioService.save(any(UsuarioDTO.class))).thenReturn(usuarioDTO);

        //Chama o método do controlador
        ResponseEntity<UsuarioDTO> response = usuarioController.save(usuarioDTO);

        // Verifica se o serviço foi chamado
        verify(usuarioService, times(1)).save(any(UsuarioDTO.class));

        // Verifica o status da respota
        assertEquals(HttpStatus.CREATED.value(), response.getStatusCodeValue());
        assertEquals(usuarioDTO, response.getBody()); //Verifica se o corpo da resposta é o usuário salvo.
    }


}
