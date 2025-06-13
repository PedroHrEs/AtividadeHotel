package com.atividade.resources;


import com.atividade.domains.Locadora;
import com.atividade.domains.dtos.LocadoraDTO;
import com.atividade.services.LocadoraService;
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
@RequestMapping(value = "/locadora")
@Tag(name = "Locadora", description = "API para Gerenciamento de Locadoras")
public class LocadoraResource {

    @Autowired
    private LocadoraService locadoraService;

    @GetMapping
    @Operation(summary = "Listar todas as Locadoras",
            description = "Retorna uma lista com todas as Locadoras cadastradas")
    public ResponseEntity<List<LocadoraDTO>> findAll(){
        return ResponseEntity.ok().body(locadoraService.findAll());
    }

    @GetMapping(value = "/{id}")
    @Operation(summary = "Busca uma locadora por id",
            description = "Realiza a busca de uma locadora cadastrada por id")
    public ResponseEntity<LocadoraDTO> findById(@PathVariable Long id){
        Locadora obj = this.locadoraService.findById(id);
        return ResponseEntity.ok().body(new LocadoraDTO(obj));
    }

    @GetMapping(value = "/cnpj/{cnpj}")
    @Operation(summary = "Busca uma locadora por cnpj",
            description = "Realiza a busca de uma locadora cadastrada por cnpj")
    public ResponseEntity<LocadoraDTO> findByCnpj(@PathVariable String cnpj){
        Locadora obj = this.locadoraService.findByCnpj(cnpj);
        return ResponseEntity.ok().body(new LocadoraDTO(obj));
    }

    @PostMapping
    @Operation(summary = "Criar uma nova locadora",
            description = "Cria uma nova locadora com base nos dados fornecidos")
    public ResponseEntity<LocadoraDTO> create(@Valid @RequestBody LocadoraDTO dto){
        Locadora locadora = locadoraService.create(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(locadora.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
    @PutMapping(value = "/{id}")
    @Operation(summary = "Altera uma Locadora",
            description = "Altera uma Locadora existente")
    public ResponseEntity<LocadoraDTO> update(@PathVariable Long id, @Valid @RequestBody LocadoraDTO objDto){
        Locadora Obj = locadoraService.update(id, objDto);
        return ResponseEntity.ok().body(new LocadoraDTO(Obj));
    }
    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Deletar uma locadora",
            description = "Remove uma locadora a partir do seu Id")
    public ResponseEntity<LocadoraDTO> delete(@PathVariable Long id){
        locadoraService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
