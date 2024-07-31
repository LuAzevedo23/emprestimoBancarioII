package com.luazevedo.emprestimoBancarioII.service;

import com.luazevedo.emprestimoBancarioII.entity.Role;
import com.luazevedo.emprestimoBancarioII.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service 
public class RoleService {

    @Autowired
    private RoleRepository repository;

    public Role save(Role role) {
        return repository.save(role);
    }

    public Role update(Role role) {
        if (repository.existsById(role.getId())) {
            return repository.save(role);
        } else {
            throw new RuntimeException("Role não encontrado");
        }
    }

    public Role findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Role não encontrado"));
    }

    public void delete(Long id) {
    }
}
