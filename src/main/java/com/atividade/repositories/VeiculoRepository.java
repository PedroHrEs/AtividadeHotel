package com.atividade.repositories;

import com.atividade.domains.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface VeiculoRepository extends JpaRepository {
    Optional<Veiculo> findByCpfProprietario(String cpf);
}
