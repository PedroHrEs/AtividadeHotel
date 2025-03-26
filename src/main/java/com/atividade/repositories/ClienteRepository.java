package com.atividade.repositories;

import com.atividade.domains.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository <Cliente, Long> {
    Optional<Cliente> findByCpf(String cpf);
    Optional<Cliente> findByCnh(String cnh);
    Optional<Cliente> findByEmail(String email);
}
