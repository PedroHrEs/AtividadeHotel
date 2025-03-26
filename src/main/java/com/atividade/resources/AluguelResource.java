package com.atividade.resources;

import com.atividade.domains.Aluguel;
import com.atividade.domains.dtos.AluguelDTO;
import com.atividade.services.AluguelService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/aluguel")
public class AluguelResource {

    @Autowired
    private AluguelService aluguelService;

    @GetMapping
    public ResponseEntity<List<AluguelDTO>> findAll(){
        return ResponseEntity.ok().body(aluguelService.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<AluguelDTO> findById(@PathVariable Long id){
        Aluguel obj = this.aluguelService.findById(id);
        return ResponseEntity.ok().body(new AluguelDTO(obj));
    }

    @GetMapping(value = "/coprovantereserva/{comprovanteReserva}")
    public ResponseEntity<AluguelDTO> findByComprovanteReserva(@PathVariable String comprovanteReserva){
        Aluguel obj = this.aluguelService.findByComprovanteReserva(comprovanteReserva);
        return ResponseEntity.ok().body(new AluguelDTO(obj));
    }

    @PostMapping
    public ResponseEntity<AluguelDTO> create(@Valid @RequestBody AluguelDTO dto){
        Aluguel Aluguel = aluguelService.create(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(Aluguel.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<AluguelDTO> update(@PathVariable Long id, @Valid @RequestBody AluguelDTO objDto){
        Aluguel Obj = aluguelService.update(id, objDto);
        return ResponseEntity.ok().body(new AluguelDTO(Obj));
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<AluguelDTO> delete(@PathVariable Long id){
        aluguelService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
