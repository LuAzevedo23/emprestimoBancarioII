package com.luazevedo.emprestimoBancarioII.repository;

import com.luazevedo.emprestimoBancarioII.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
