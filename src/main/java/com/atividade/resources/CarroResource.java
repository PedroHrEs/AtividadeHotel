package com.atividade.resources;


import com.atividade.domains.Carro;
import com.atividade.domains.dtos.CarroDTO;
import com.atividade.services.CarroService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/carro")
@Tag(name = "Carro", description = "API para Gerenciamento de carros")
public class CarroResource {

    @Autowired
    private CarroService carroService;

    @GetMapping
    @Operation(summary = "Listar todos os carros",
            description = "Retorna uma lista com todos os carros cadastrados")
    public ResponseEntity<List<CarroDTO>> findAll(){
        return ResponseEntity.ok().body(carroService.findAll());
    }

    @GetMapping(value = "/{id}")
    @Operation(summary = "Busca um carro por id",
            description = "Realiza a busca de um carro cadastrado por id")
    public ResponseEntity<CarroDTO> findById(@PathVariable Long id){
        Carro obj = this.carroService.findById(id);
        return ResponseEntity.ok().body(new CarroDTO(obj));
    }

    @GetMapping(value = "/placa/{placa}")
    @Operation(summary = "Busca um carro por placa",
            description = "Realiza a busca de um carro cadastrado por Placa")
    public ResponseEntity<CarroDTO> findByPlaca(@PathVariable String placa){
        Carro obj = this.carroService.findByPlaca(placa);
        return ResponseEntity.ok().body(new CarroDTO(obj));
    }

    @PostMapping
    @Operation(summary = "Criar um novo carro",
            description = "Cria um novo carro com base nos dados fornecidos")
    public ResponseEntity<CarroDTO> create(@Valid @RequestBody CarroDTO dto){
        Carro carro = carroService.create(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(carro.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
    @PutMapping(value = "/{id}")
    @Operation(summary = "Altera um carro",
            description = "Altera um carro existente")
    public ResponseEntity<CarroDTO> update(@PathVariable Long id, @Valid @RequestBody CarroDTO objDto){
        Carro Obj = carroService.update(id, objDto);
        return ResponseEntity.ok().body(new CarroDTO(Obj));
    }
    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Deletar um Carro",
            description = "Remove um carro a partir do seu Id")
    public ResponseEntity<CarroDTO> delete(@PathVariable Long id){
        carroService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
