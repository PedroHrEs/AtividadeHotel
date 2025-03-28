package com.atividade.services;

import com.atividade.domains.Veiculo;
import com.atividade.domains.dtos.VeiculoDTO;
import com.atividade.repositories.VeiculoRepository;
import com.atividade.services.exceptions.DataIntegrityViolationException;
import com.atividade.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VeiculoService {
    @Autowired
    private VeiculoRepository veiculoRepo;

    public List<VeiculoDTO> findAll(){
        return veiculoRepo.findAll().stream().map(obj -> new VeiculoDTO(obj)).collect(Collectors.toList());
    }
    public Veiculo findById(Long id){
        Optional<Veiculo> obj = veiculoRepo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Veiculo não encontrado! id: "+id));
    }
    public Veiculo findByCpfProprietario(String cpf){
        Optional<Veiculo> obj = veiculoRepo.findByCpfProprietario(cpf);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Veiculo não encontrado cpf proprietario:"+cpf));
    }
    public Veiculo create(VeiculoDTO dto){
        dto.setId(null);
        validaVeiculo(dto);
        Veiculo obj = new Veiculo(dto);
        return veiculoRepo.save(obj);
    }
    public Veiculo update(Long id, VeiculoDTO objDto){
        objDto.setId(id);
        Veiculo oldObj = findById(id);
        oldObj = new Veiculo(objDto);
        return veiculoRepo.save(oldObj);
    }
    public void delete(Long id){
        Veiculo obj = findById(id);
        veiculoRepo.deleteById(id);
    }

    private void validaVeiculo(VeiculoDTO dto) {
        Optional<Veiculo> obj = veiculoRepo.findByCpfProprietario(dto.getCpfProprietario());
        if (obj.isPresent() && obj.get().getId() != dto.getId()) {
            throw new DataIntegrityViolationException("Cpf já cadastrado!");
        }
    }
}
