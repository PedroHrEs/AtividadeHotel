package com.atividade.repositories;

import com.atividade.domains.Reservante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReservanteRepository extends JpaRepository<Reservante, Long> {
    Optional<Reservante> findByCpf(String cpf);
    Optional<Reservante> findByEmail(String email);
}
