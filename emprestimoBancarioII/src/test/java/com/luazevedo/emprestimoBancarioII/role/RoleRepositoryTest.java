package com.luazevedo.emprestimoBancarioII.role;

import com.luazevedo.emprestimoBancarioII.entity.Role;
import com.luazevedo.emprestimoBancarioII.repository.RoleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Classe de teste de integração para o repositório Role.
 * Utiliza o contexto completo do Spring.
 */
@SpringBootTest
public class RoleRepositoryTest {

    @Autowired
    private RoleRepository roleRepository;

    /**
     * Testa a funcionalidade de salvar uma role no banco de dados.
     * Verifica se o ID é gerado corretamente.
     */

    @Test
    public void testSalvarRole(){
        //Cria uma nova role
        Role role = new Role();
        role.setNome("ADMIN");

        //Salva a role e obtém o resultado
        Role resultado = roleRepository.save(role);

        //Verifica se o ID foi gerado e os campos estão corretos.
        assertNotNull(resultado.getId());
        assertEquals("ADMIN", resultado.getNome());

    }
}
