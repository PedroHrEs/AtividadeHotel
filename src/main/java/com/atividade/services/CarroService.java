package com.atividade.services;

import com.atividade.domains.Aluguel;
import com.atividade.domains.Carro;
import com.atividade.domains.Cliente;
import com.atividade.domains.Locadora;
import com.atividade.domains.dtos.AluguelDTO;
import com.atividade.domains.dtos.CarroDTO;
import com.atividade.repositories.AluguelRepository;
import com.atividade.repositories.CarroRepository;
import com.atividade.repositories.ClienteRepository;
import com.atividade.repositories.LocadoraRepository;
import com.atividade.services.exceptions.DataIntegrityViolationException;
import com.atividade.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CarroService {

    @Autowired
    private CarroRepository carroRepo;

    @Autowired
    private LocadoraRepository locadoraRepo;

    public List<CarroDTO> findAll(){
        return carroRepo.findAll().stream().map(obj -> new CarroDTO(obj)).collect(Collectors.toList());

    }
    public Carro findById(Long id){
        Optional<Carro> obj = carroRepo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Carro não encontrado! id: "+id));
    }
    public Carro findByPlaca(String placa){
        Optional<Carro> obj = carroRepo.findByPlaca(placa);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Carro não encontrado placa:"+placa));
    }
    public Carro create( CarroDTO dto){
        dto.setId(null);
        validaCarro(dto);
        Carro obj = new Carro(dto);
        return carroRepo.save(obj);
    }
    public Carro update(Long id, CarroDTO objDto){
        objDto.setId(id);
        Carro oldObj = findById(id);
        oldObj = new Carro(objDto);
        return carroRepo.save(oldObj);
    }
    public void delete(Long id){
        Carro obj = findById(id);
        carroRepo.deleteById(id);
    }

    private void validaCarro(CarroDTO dto) {
        Optional<Carro> obj = carroRepo.findByPlaca(dto.getPlaca());
        if (obj.isPresent() && obj.get().getId() != dto.getId()) {
            throw new DataIntegrityViolationException("Placa já cadastrada!");
        }
        Optional<Locadora> locadora = locadoraRepo.findById(dto.getLocadora());
        if (!locadora.isPresent()) {
            throw new DataIntegrityViolationException("Locadora - " + dto.getLocadora() + " não está cadastrado!");
        }
    }
}