package com.atividade.resources;

import com.atividade.domains.Reserva;
import com.atividade.domains.dtos.ReservaDTO;
import com.atividade.services.ReservaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/reserva")
public class ReservaResource {

    @Autowired
    private ReservaService reservaService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<ReservaDTO> findById(@PathVariable UUID id){
        Reserva obj = this.reservaService.findById(id);
        return ResponseEntity.ok().body(new ReservaDTO(obj));
    }

    @GetMapping
    public ResponseEntity<List<ReservaDTO>> findAll(){return ResponseEntity.ok().body(reservaService.findAll());}

    @PostMapping
    public ResponseEntity<ReservaDTO> create(@Valid @RequestBody ReservaDTO objDto){
        Reserva newObj = reservaService.create(objDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
    @PutMapping(value="/{id}")
    public ResponseEntity<ReservaDTO> update(@PathVariable UUID id, @Valid @RequestBody ReservaDTO objDto){
        Reserva Obj = reservaService.update(id, objDto);
        return ResponseEntity.ok().body(new ReservaDTO(Obj));
    }
}
