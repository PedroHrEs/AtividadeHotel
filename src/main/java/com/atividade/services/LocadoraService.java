package com.atividade.services;




import com.atividade.domains.Locadora;
import com.atividade.domains.dtos.LocadoraDTO;
import com.atividade.repositories.LocadoraRepository;
import com.atividade.services.exceptions.DataIntegrityViolationException;
import com.atividade.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LocadoraService {

    @Autowired
    private LocadoraRepository locadoraRepo;

    public List<LocadoraDTO> findAll(){
        return locadoraRepo.findAll().stream().map(obj -> new LocadoraDTO(obj)).collect(Collectors.toList());

    }
    public Locadora findById(Long id){
        Optional<Locadora> obj = locadoraRepo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Locadora não encontrado! id: "+id));
    }
    public Locadora findByCnpj(String cnpj){
        Optional<Locadora> obj = locadoraRepo.findByCnpj(cnpj);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Locadora não encontrado comprovante Reserva:"+cnpj));
    }
    public Locadora create(LocadoraDTO dto){
        dto.setId(null);
        validaLocadora(dto);
        Locadora obj = new Locadora(dto);
        return locadoraRepo.save(obj);
    }
    public Locadora update(Long id, LocadoraDTO objDto){
        objDto.setId(id);
        Locadora oldObj = findById(id);
        oldObj = new Locadora(objDto);
        return locadoraRepo.save(oldObj);
    }
    public void delete(Long id){
        Locadora obj = findById(id);
        locadoraRepo.deleteById(id);
    }

    private void validaLocadora(LocadoraDTO dto) {
        Optional<Locadora> obj = locadoraRepo.findByCnpj(dto.getCnpj());
        if (obj.isPresent() && obj.get().getId() != dto.getId()) {
            throw new DataIntegrityViolationException("Cnpj já cadastrado!");
        }
    }
}
