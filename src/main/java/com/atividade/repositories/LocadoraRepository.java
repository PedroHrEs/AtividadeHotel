package com.atividade.repositories;

import com.atividade.domains.Aluguel;
import com.atividade.domains.Locadora;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LocadoraRepository extends JpaRepository<Locadora, Long> {
    Optional<Locadora> findByCnpj(String cnpj);
}
