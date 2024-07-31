package com.luazevedo.emprestimoBancarioII.repository;

import com.luazevedo.emprestimoBancarioII.entity.Emprestimo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {
}
