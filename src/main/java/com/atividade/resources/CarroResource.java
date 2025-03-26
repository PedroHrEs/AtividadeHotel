package com.atividade.resources;


import com.atividade.domains.Carro;
import com.atividade.domains.dtos.CarroDTO;
import com.atividade.services.CarroService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/carro")
public class CarroResource {

    @Autowired
    private CarroService carroService;

    @GetMapping
    public ResponseEntity<List<CarroDTO>> findAll(){
        return ResponseEntity.ok().body(carroService.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CarroDTO> findById(@PathVariable Long id){
        Carro obj = this.carroService.findById(id);
        return ResponseEntity.ok().body(new CarroDTO(obj));
    }

    @GetMapping(value = "/placa/{placa}")
    public ResponseEntity<CarroDTO> findByPlaca(@PathVariable String placa){
        Carro obj = this.carroService.findByPlaca(placa);
        return ResponseEntity.ok().body(new CarroDTO(obj));
    }

    @PostMapping
    public ResponseEntity<CarroDTO> create(@Valid @RequestBody CarroDTO dto){
        Carro carro = carroService.create(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(carro.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<CarroDTO> update(@PathVariable Long id, @Valid @RequestBody CarroDTO objDto){
        Carro Obj = carroService.update(id, objDto);
        return ResponseEntity.ok().body(new CarroDTO(Obj));
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<CarroDTO> delete(@PathVariable Long id){
        carroService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
