package com.atividade.services;


import com.atividade.domains.Cliente;
import com.atividade.domains.dtos.ClienteDTO;
import com.atividade.repositories.ClienteRepository;
import com.atividade.services.exceptions.DataIntegrityViolationException;
import com.atividade.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepo;

    public List<ClienteDTO> findAll(){
        return clienteRepo.findAll().stream().map(obj -> new ClienteDTO(obj)).collect(Collectors.toList());

    }
    public Cliente findById(Long id){
        Optional<Cliente> obj = clienteRepo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Cliente não encontrado! id: "+id));
    }
    public Cliente findByCpf(String cpf){
        Optional<Cliente> obj = clienteRepo.findByCpf(cpf);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Locadora não encontrado comprovante Reserva:"+cpf));
    }
    public Cliente findByCnh(String cnh){
        Optional<Cliente> obj = clienteRepo.findByCnh(cnh);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Locadora não encontrado comprovante Reserva:"+cnh));
    }
    public Cliente findByEmail(String email){
        Optional<Cliente> obj = clienteRepo.findByEmail(email);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Locadora não encontrado comprovante Reserva:"+email));
    }
    public Cliente create(ClienteDTO dto){
        dto.setId(null);
        validaCliente(dto);
        Cliente obj = new Cliente(dto);
        return clienteRepo.save(obj);
    }
    public Cliente update(Long id, ClienteDTO objDto){
        objDto.setId(id);
        Cliente oldObj = findById(id);
        oldObj = new Cliente(objDto);
        return clienteRepo.save(oldObj);
    }
    public void delete(Long id){
        Cliente obj = findById(id);
        clienteRepo.deleteById(id);
    }

    private void validaCliente(ClienteDTO dto) {
        Optional<Cliente> obj = clienteRepo.findByCpf(dto.getCpf());
        if (obj.isPresent() && obj.get().getId() != dto.getId()) {
            throw new DataIntegrityViolationException("Cpf já cadastrado!");
        }
        obj = clienteRepo.findByCnh(dto.getCnh());
        if (obj.isPresent() && obj.get().getId() != dto.getId()) {
            throw new DataIntegrityViolationException("Cnh já cadastrado!");
        }
        obj = clienteRepo.findByEmail(dto.getEmail());
        if (obj.isPresent() && obj.get().getId() != dto.getId()) {
            throw new DataIntegrityViolationException("Email já cadastrado!");
        }
    }
}
