package com.atividade.resources;

import com.atividade.domains.Reservante;
import com.atividade.domains.dtos.ReservanteDTO;
import com.atividade.services.ReservanteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
@RestController
@RequestMapping(value = "/reservante")
public class ReservanteResource {
    @Autowired
    private ReservanteService reservanteService;

    @GetMapping
    public ResponseEntity<List<ReservanteDTO>> findAll(){ return ResponseEntity.ok().body(reservanteService.findAll());}

    @GetMapping(value = "/{id}")
    public ResponseEntity<ReservanteDTO> findById(@PathVariable Long id){
        Reservante obj = this.reservanteService.findById(id);
        return ResponseEntity.ok().body(new ReservanteDTO(obj));
    }

    @GetMapping(value = "/cpf/{cpf}")
    public ResponseEntity<ReservanteDTO> findByCpf(@PathVariable String cpf){
        Reservante obj = this.reservanteService.findByCpf(cpf);
        return ResponseEntity.ok().body(new ReservanteDTO(obj));
    }

    @GetMapping(value = "/email/{email}")
    public ResponseEntity<ReservanteDTO> findByEmail(@PathVariable String email){
        Reservante obj = this.reservanteService.findByEmail(email);
        return ResponseEntity.ok().body(new ReservanteDTO(obj));
    }
    @PostMapping
    public ResponseEntity<ReservanteDTO> create(@Valid @RequestBody ReservanteDTO objDto){
        Reservante newObj = reservanteService.create(objDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<ReservanteDTO> update(@PathVariable Long id, @Valid @RequestBody ReservanteDTO objDto){
        Reservante obj = reservanteService.update(id, objDto);
        return ResponseEntity.ok().body(new ReservanteDTO(obj));
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<ReservanteDTO> delete(@PathVariable Long id){
        reservanteService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
