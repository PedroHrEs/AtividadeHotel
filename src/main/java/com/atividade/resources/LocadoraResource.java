package com.atividade.resources;


import com.atividade.domains.Locadora;
import com.atividade.domains.dtos.LocadoraDTO;
import com.atividade.services.LocadoraService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/locadora")
public class LocadoraResource {

    @Autowired
    private LocadoraService locadoraService;

    @GetMapping
    public ResponseEntity<List<LocadoraDTO>> findAll(){
        return ResponseEntity.ok().body(locadoraService.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<LocadoraDTO> findById(@PathVariable Long id){
        Locadora obj = this.locadoraService.findById(id);
        return ResponseEntity.ok().body(new LocadoraDTO(obj));
    }

    @GetMapping(value = "/cnpj/{cnpj}")
    public ResponseEntity<LocadoraDTO> findByCnpj(@PathVariable String cnpj){
        Locadora obj = this.locadoraService.findByCnpj(cnpj);
        return ResponseEntity.ok().body(new LocadoraDTO(obj));
    }

    @PostMapping
    public ResponseEntity<LocadoraDTO> create(@Valid @RequestBody LocadoraDTO dto){
        Locadora locadora = locadoraService.create(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(locadora.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<LocadoraDTO> update(@PathVariable Long id, @Valid @RequestBody LocadoraDTO objDto){
        Locadora Obj = locadoraService.update(id, objDto);
        return ResponseEntity.ok().body(new LocadoraDTO(Obj));
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<LocadoraDTO> delete(@PathVariable Long id){
        locadoraService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
