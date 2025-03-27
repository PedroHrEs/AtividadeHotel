package com.atividade.services;

import com.atividade.domains.Reserva;
import com.atividade.domains.Reservante;
import com.atividade.domains.dtos.ReservanteDTO;
import com.atividade.repositories.ReservanteRepository;
import com.atividade.services.exceptions.DataIntegrityViolationException;
import com.atividade.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class ReservanteService {

    @Autowired
    private ReservanteRepository reservanteRepo;

    public List<ReservanteDTO> findAll() {
        return reservanteRepo.findAll().stream().map(obj -> new ReservanteDTO(obj)).collect(Collectors.toList());
    }

    public Reservante findById(Long id) {
        Optional<Reservante> obj = reservanteRepo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! id: " + id));
    }

    public Reservante findByCpf(String cpf) {
        Optional<Reservante> obj = reservanteRepo.findByCpf(cpf);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! cpf: " + cpf));
    }

    public Reservante findByEmail(String email) {
        Optional<Reservante> obj = reservanteRepo.findByEmail(email);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Email: " + email));
    }

    public Reservante create(ReservanteDTO objDto) {
        objDto.setId(null);
        ValidaPorCPFeEmail(objDto);
        Reservante newObj = new Reservante(objDto);
        return reservanteRepo.save(newObj);
    }

    public Reservante update(Long id, ReservanteDTO objDTO) {
        objDTO.setId(id);
        Reservante oldObj = findById(id);
        ValidaPorCPFeEmail(objDTO);
        oldObj = new Reservante(objDTO);
        return reservanteRepo.save(oldObj);
    }

    public void delete(Long id) {
        Reservante obj = findById(id);
        if (obj.getReserva().size() > 0) {
            throw new DataIntegrityViolationException("Reservante não pode ser deletado pois possui Reservas!");
        }
        reservanteRepo.deleteById(id);
    }

    public void ValidaPorCPFeEmail(ReservanteDTO objDto) {
        Optional<Reservante> obj = reservanteRepo.findByCpf(objDto.getCpf());
        if (obj.isPresent() && obj.get().getId() != objDto.getId()) {
            throw new DataIntegrityViolationException("CPF já cadastrado no sistema");
        }
        obj = reservanteRepo.findByEmail(objDto.getEmail());
        if (obj.isPresent() && obj.get().getId() != objDto.getId()) {
            throw new DataIntegrityViolationException("Email já cadastrado no sistema");
        }
    }
}
