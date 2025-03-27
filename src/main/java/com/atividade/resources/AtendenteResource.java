package com.atividade.resources;

import com.atividade.domains.Atendente;
import com.atividade.domains.dtos.AtendenteDTO;
import com.atividade.services.AtendenteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
@RestController
@RequestMapping(value = "/atendente")
public class AtendenteResource {
    @Autowired
    private AtendenteService atendenteService;

    @GetMapping
    public ResponseEntity<List<AtendenteDTO>> findAll(){ return ResponseEntity.ok().body(atendenteService.findAll());}

    @GetMapping(value = "/{id}")
    public ResponseEntity<AtendenteDTO> findById(@PathVariable Long id){
        Atendente obj = this.atendenteService.findById(id);
        return ResponseEntity.ok().body(new AtendenteDTO(obj));
    }

    @GetMapping(value = "/cpf/{cpf}")
    public ResponseEntity<AtendenteDTO> findByCpf(@PathVariable String cpf){
        Atendente obj = this.atendenteService.findByCpf(cpf);
        return ResponseEntity.ok().body(new AtendenteDTO(obj));
    }

    @GetMapping(value = "/email/{email}")
    public ResponseEntity<AtendenteDTO> findByEmail(@PathVariable String email){
        Atendente obj = this.atendenteService.findByEmail(email);
        return ResponseEntity.ok().body(new AtendenteDTO(obj));
    }
    @PostMapping
    public ResponseEntity<AtendenteDTO> create(@Valid @RequestBody AtendenteDTO objDto){
        Atendente newObj = atendenteService.create(objDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<AtendenteDTO> update(@PathVariable Long id, @Valid @RequestBody AtendenteDTO objDto){
        Atendente obj = atendenteService.update(id, objDto);
        return ResponseEntity.ok().body(new AtendenteDTO(obj));
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<AtendenteDTO> delete(@PathVariable Long id){
        atendenteService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
