package com.atividade.repositories;

import com.atividade.domains.Atendente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AtendenteRepository extends JpaRepository<Atendente, Long> {
}
