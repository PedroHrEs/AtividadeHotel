package com.atividade.resources;

import com.atividade.domains.Reservante;
import com.atividade.domains.dtos.ReservanteDTO;
import com.atividade.services.ReservanteService;
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
@RequestMapping(value = "/reservante")
@Tag(name = "Reservante", description = "API para Gerenciamento de Reservantes")
public class ReservanteResource {
    @Autowired
    private ReservanteService reservanteService;

    @GetMapping
    @Operation(summary = "Listar todos os Reservantes",
            description = "Retorna uma lista com todos os Reservantes cadastrados")
    public ResponseEntity<List<ReservanteDTO>> findAll(){ return ResponseEntity.ok().body(reservanteService.findAll());}

    @GetMapping(value = "/{id}")
    @Operation(summary = "Busca um reservante por id",
            description = "Realiza a busca de um reservante cadastrado por id")
    public ResponseEntity<ReservanteDTO> findById(@PathVariable Long id){
        Reservante obj = this.reservanteService.findById(id);
        return ResponseEntity.ok().body(new ReservanteDTO(obj));
    }

    @GetMapping(value = "/cpf/{cpf}")
    @Operation(summary = "Busca um reservante por CPF",
            description = "Realiza a busca de um reservante cadastrado por CPF")
    public ResponseEntity<ReservanteDTO> findByCpf(@PathVariable String cpf){
        Reservante obj = this.reservanteService.findByCpf(cpf);
        return ResponseEntity.ok().body(new ReservanteDTO(obj));
    }

    @GetMapping(value = "/email/{email}")
    @Operation(summary = "Busca um reservante por Email",
            description = "Realiza a busca de um reservante cadastrado por Email")
    public ResponseEntity<ReservanteDTO> findByEmail(@PathVariable String email){
        Reservante obj = this.reservanteService.findByEmail(email);
        return ResponseEntity.ok().body(new ReservanteDTO(obj));
    }
    @PostMapping
    @Operation(summary = "Criar um novo reservante",
            description = "Cria um novo reservante com base nos dados fornecidos")
    public ResponseEntity<ReservanteDTO> create(@Valid @RequestBody ReservanteDTO objDto){
        Reservante newObj = reservanteService.create(objDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
    @PutMapping(value = "/{id}")
    @Operation(summary = "Altera um reservante",
            description = "Altera um reservante existente")
    public ResponseEntity<ReservanteDTO> update(@PathVariable Long id, @Valid @RequestBody ReservanteDTO objDto){
        Reservante obj = reservanteService.update(id, objDto);
        return ResponseEntity.ok().body(new ReservanteDTO(obj));
    }
    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Deletar um reservante",
            description = "Remove um reservante a partir do seu Id")
    public ResponseEntity<ReservanteDTO> delete(@PathVariable Long id){
        reservanteService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
