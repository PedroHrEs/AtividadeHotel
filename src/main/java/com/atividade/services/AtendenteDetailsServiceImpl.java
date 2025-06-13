package com.atividade.services;

import com.atividade.domains.Atendente;
import com.atividade.repositories.AtendenteRepository;
import com.atividade.security.AtendenteSS;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AtendenteDetailsServiceImpl implements UserDetailsService {

    private final AtendenteRepository atendenteRepository;


    public AtendenteDetailsServiceImpl(AtendenteRepository atendenteRepository) {
        this.atendenteRepository = atendenteRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Atendente> atendente = atendenteRepository.findByEmail(username);

        if (atendente.isEmpty()) {
            throw new UsernameNotFoundException("User not found: " + username);
        }
        return new AtendenteSS(atendente.orElse(null));
    }
}