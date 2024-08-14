package com.luazevedo.emprestimoBancarioII.service;

import com.luazevedo.emprestimoBancarioII.entity.Role;
import com.luazevedo.emprestimoBancarioII.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Serviço para manipulação das entidades {@link Role}.
 */
@Service
public class RoleService {

    private final RoleRepository repository;

    @Autowired
    public RoleService(RoleRepository repository) {
        this.repository = repository;
    }

    /**
     * Salva uma nova instância de {@link Role}.
     *
     * @param role a instância de Role a ser salva
     * @return a instância salva de Role
     */
    public Role save(Role role) {
        return repository.save(role);
    }

    /**
     * Atualiza uma instância existente de {@link Role}.
     *
     * @param role a instância de Role a ser atualizada
     * @return a instância atualizada de Role
     * @throws RuntimeException se o Role não for encontrado
     */
    public Role update(Role role) {
        if (repository.existsById(role.getId())) {
            return repository.save(role);
        } else {
            throw new RuntimeException("Role não encontrado");
        }
    }

    /**
     * Encontra uma instância de {@link Role} pelo ID.
     *
     * @param id o ID do Role
     * @return a instância de Role encontrada
     * @throws RuntimeException se o Role não for encontrado
     */
    public Role findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Role não encontrado"));
    }

    /**
     * Deleta uma instância de {@link Role} pelo ID.
     *
     * @param id o ID do Role
     * @throws RuntimeException se o Role não for encontrado
     */
    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new RuntimeException("Role não encontrado");
        }
    }
}
