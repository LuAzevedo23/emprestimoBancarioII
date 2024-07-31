package com.luazevedo.emprestimoBancarioII.repository;

import com.luazevedo.emprestimoBancarioII.entity.Garantia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GarantiaRepository extends JpaRepository<Garantia, Long> {
}
