package com.atividade.repositories;

import com.atividade.domains.Reservante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservanteRepository extends JpaRepository<Reservante, Long> {
}
