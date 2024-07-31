package com.luazevedo.emprestimoBancarioII.repository;

import com.luazevedo.emprestimoBancarioII.entity.Configuracao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfiguracaoRepository extends JpaRepository<Configuracao, Long> {
}
