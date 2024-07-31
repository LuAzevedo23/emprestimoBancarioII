package com.luazevedo.emprestimoBancarioII.repository;

import com.luazevedo.emprestimoBancarioII.entity.HistoricoEmprestimo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoricoEmprestimoRepository extends JpaRepository<HistoricoEmprestimo, Long> {
}
