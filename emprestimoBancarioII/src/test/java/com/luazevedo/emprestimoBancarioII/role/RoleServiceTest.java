package com.luazevedo.emprestimoBancarioII.role;

import com.luazevedo.emprestimoBancarioII.entity.Role;
import com.luazevedo.emprestimoBancarioII.repository.RoleRepository;
import com.luazevedo.emprestimoBancarioII.service.RoleService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * Classe de teste para o serviço de Role.
 * Utiliza Mockito para simular as dependências.
 */
@ExtendWith(MockitoExtension.class)
public class RoleServiceTest {

    @InjectMocks
    private RoleService roleService;

    @Mock
    private RoleRepository roleRepository;

    /**
     * Testa o método de salvar uma role.
     * Veriica se o método do repositório foi chamado corretamente.
     */
    @Test
    public void testSalvarRole() {
        //Cria uma role de exemplo
        Role role = new Role();
        role.setNome("ADMIN");

        // Define o comportamento do mock
        when(roleRepository.save(any(Role.class))).thenReturn(role);

        Role resultado = roleService.salvarRole(role);

        //Verifica o resultado
        assertEquals("ADMIN", resultado.getNome());
    }
}
