package com.atividade.repositories;

import com.atividade.domains.Aluguel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AluguelRepository extends JpaRepository<Aluguel, Long> {
    Optional<Aluguel> findByComprovanteReserva(String comprovanteReserva);

}
