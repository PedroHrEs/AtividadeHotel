package com.atividade.services;

import com.atividade.domains.Aluguel;
import com.atividade.domains.Carro;
import com.atividade.domains.Cliente;
import com.atividade.domains.dtos.AluguelDTO;
import com.atividade.repositories.AluguelRepository;
import com.atividade.repositories.CarroRepository;
import com.atividade.repositories.ClienteRepository;
import com.atividade.services.exceptions.DataIntegrityViolationException;
import com.atividade.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AluguelService {

    @Autowired
    private AluguelRepository aluguelRepo;

    @Autowired
    private CarroRepository carroRepo;

    @Autowired
    private ClienteRepository clienteRepo;

    public List<AluguelDTO> findAll(){
        return aluguelRepo.findAll().stream().map(obj -> new AluguelDTO(obj)).collect(Collectors.toList());

    }
    public Aluguel findById(Long id){
        Optional<Aluguel> obj = aluguelRepo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Aluguel não encontrado! id: "+id));
    }
    public Aluguel findByComprovanteReserva(String comprovanteReserva){
        Optional<Aluguel> obj = aluguelRepo.findByComprovanteReserva(comprovanteReserva);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Aluguel não encontrado comprovante Reserva:"+comprovanteReserva));
    }
    public Aluguel create( AluguelDTO dto){
        dto.setId(null);
        validaAluguel(dto);
        Aluguel obj = new Aluguel(dto);
        return aluguelRepo.save(obj);
    }
    public Aluguel update(Long id, AluguelDTO objDto){
        objDto.setId(id);
        Aluguel oldObj = findById(id);
        oldObj = new Aluguel(objDto);
        return aluguelRepo.save(oldObj);
    }
    public void delete(Long id){
        Aluguel obj = findById(id);
        aluguelRepo.deleteById(id);
    }

    private void validaAluguel(AluguelDTO dto) {
        Optional<Aluguel> obj = aluguelRepo.findByComprovanteReserva(dto.getComprovanteReserva());
        if (obj.isPresent() && obj.get().getId() != dto.getId()) {
            throw new DataIntegrityViolationException("Código de barras já cadastrado!");
        }
        Optional<Carro> carro = carroRepo.findById(dto.getCarro());
        if (!carro.isPresent()) {
            throw new DataIntegrityViolationException("Carro - " + dto.getCarro() + " não está cadastrado!");
        }
        Optional<Cliente> cliente = clienteRepo.findById(dto.getCliente());
        if (!cliente.isPresent()) {
            throw new DataIntegrityViolationException("Cliente - " + dto.getCliente() + " não está cadastrado!");
        }
    }
}
