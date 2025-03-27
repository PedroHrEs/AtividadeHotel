package com.atividade.services;

import com.atividade.domains.Atendente;
import com.atividade.domains.dtos.AtendenteDTO;
import com.atividade.repositories.AtendenteRepository;
import com.atividade.services.exceptions.DataIntegrityViolationException;
import com.atividade.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AtendenteService {

    @Autowired
    private AtendenteRepository atendenteRepo;

    public List<AtendenteDTO> findAll() {
        return atendenteRepo.findAll().stream().map(obj -> new AtendenteDTO(obj)).collect(Collectors.toList());
    }

    public Atendente findById(Long id) {
        Optional<Atendente> obj = atendenteRepo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! id: " + id));
    }

    public Atendente findByCpf(String cpf) {
        Optional<Atendente> obj = atendenteRepo.findByCpf(cpf);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! cpf: " + cpf));
    }

    public Atendente findByEmail(String email) {
        Optional<Atendente> obj = atendenteRepo.findByEmail(email);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Email: " + email));
    }

    public Atendente create(AtendenteDTO objDto) {
        objDto.setId(null);
        ValidaPorCPFeEmail(objDto);
        Atendente newObj = new Atendente(objDto);
        return atendenteRepo.save(newObj);
    }

    public Atendente update(Long id, AtendenteDTO objDTO) {
        objDTO.setId(id);
        Atendente oldObj = findById(id);
        ValidaPorCPFeEmail(objDTO);
        oldObj = new Atendente(objDTO);
        return atendenteRepo.save(oldObj);
    }

    public void delete(Long id) {
        Atendente obj = findById(id);
        if (obj.getReserva().size() > 0) {
            throw new DataIntegrityViolationException("Atendente não pode ser deletado pois possui Reservas!");
        }
        atendenteRepo.deleteById(id);
    }

    public void ValidaPorCPFeEmail(AtendenteDTO objDto) {
        Optional<Atendente> obj = atendenteRepo.findByCpf(objDto.getCpf());
        if (obj.isPresent() && obj.get().getId() != objDto.getId()) {
            throw new DataIntegrityViolationException("CPF já cadastrado no sistema");
        }
        obj = atendenteRepo.findByEmail(objDto.getEmail());
        if (obj.isPresent() && obj.get().getId() != objDto.getId()) {
            throw new DataIntegrityViolationException("Email já cadastrado no sistema");
        }
    }
}
