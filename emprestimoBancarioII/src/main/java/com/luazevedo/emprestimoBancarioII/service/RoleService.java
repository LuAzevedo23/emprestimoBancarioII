package com.luazevedo.emprestimoBancarioII.service;

import com.luazevedo.emprestimoBancarioII.entity.Role;
import com.luazevedo.emprestimoBancarioII.exception.RoleNotFoundException;
import com.luazevedo.emprestimoBancarioII.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Serviço para operações relacionadas a roles.
 * Contém métodos para gerenciar operações de roles.
 *
 * @see Role
 *
 */
@Service
public class RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    /**
     * Salva uma nova role no repositório.
     *
     * @param role a role a ser salva
     * @return a role salva
     */
    public Role salvarRole (Role role) {
        return roleRepository.save(role);
    }

    /**
     * Exclui uma role pela ID.
     *
     * @param id o ID da rele a ser excluída.
     * @throws RoleNotFoundException se a role não for encontrada.
     */

    public void delete(Long id) throws RoleNotFoundException {
        Role role = roleRepository.findById(id)
                .orElseThrow(()-> new RoleNotFoundException("Role não encontrado com ID: " + id));
        roleRepository.delete(role);
    }

    /**
     *Encontra todas as roles existentes no sistema.
     *
     * @return Uma lista de todas as roles.
     */
    public List<Role> findAll(){
        return roleRepository.findAll();
    }
    /**
     * Encontra uma role específica pelo ID.
     *
     * @param id o ID da role a ser encontrada.
     * @return A role encontrada, se existir.
     * @throws RoleNotFoundException Se a role não for encontrada.
     */
    public Role findById(Long id){
        return roleRepository.findById(id)
                .orElseThrow(()-> new RoleNotFoundException("Role não encontrada com ID: " + id));
    }
    /**
     * Salva uma nova role ou atualiza uma role existente.
     *
     * @param role A role a ser salva ou atualizada.
     * @return A role salva ou atualizada.
     */
    public Role save(Role role) {
        return roleRepository.save(role);
    }

    /**
     * Deleta um role pelo ID.
     *
     * @param id o ID da role a ser deletada.
     * @throws RoleNotFoundException Se a role não for encontrada.
     */
    public void deleteById(Long id){
        if(!roleRepository.existsById(id)){
            throw new RoleNotFoundException("Role não encontrada com ID: " + id);
        }
        roleRepository.deleteById(id);
    }
    /**
     * Atualiza os dados de um role existente.
     *
     * @param id o ID da role a ser atualizada.
     * @param role A role com os novos dados.
     * @return A role atualizada.
     * @throws RoleNotFoundException Se a role não for encontrada.
     */
    public Role update(Long id, Role role) {
        Role existingRole = roleRepository.findById(id)
                .orElseThrow(() -> new RoleNotFoundException("Role não encontrado com ID: " + id));

        // Atualize os campos do existingRole com os dados do role recebido
        existingRole.setNome(role.getNome());
        // Adicione outras atualizações conforme necessário

        return roleRepository.save(existingRole); // Salva e retorna o objeto atualizado
    }
}