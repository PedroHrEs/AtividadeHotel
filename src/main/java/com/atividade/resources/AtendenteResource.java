package com.atividade.resources;

import com.atividade.domains.Atendente;
import com.atividade.domains.dtos.AtendenteDTO;
import com.atividade.services.AtendenteService;
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
@RequestMapping(value = "/atendente")
@Tag(name = "Atendente", description = "API para Gerenciamento de Atendentes")
public class AtendenteResource {
    @Autowired
    private AtendenteService atendenteService;

    @GetMapping
    @Operation(summary = "Listar todos os Atendentes",
            description = "Retorna uma lista com todos os atendentes cadastrados")
    public ResponseEntity<List<AtendenteDTO>> findAll(){ return ResponseEntity.ok().body(atendenteService.findAll());}

    @GetMapping(value = "/{id}")
    @Operation(summary = "Busca um atendente por id",
            description = "Realiza a busca de um atendente cadastrado por id")
    public ResponseEntity<AtendenteDTO> findById(@PathVariable Long id){
        Atendente obj = this.atendenteService.findById(id);
        return ResponseEntity.ok().body(new AtendenteDTO(obj));
    }

    @GetMapping(value = "/cpf/{cpf}")
    @Operation(summary = "Busca um atendente por CPF",
            description = "Realiza a busca de um atendente cadastrado por CPF")
    public ResponseEntity<AtendenteDTO> findByCpf(@PathVariable String cpf){
        Atendente obj = this.atendenteService.findByCpf(cpf);
        return ResponseEntity.ok().body(new AtendenteDTO(obj));
    }

    @GetMapping(value = "/email/{email}")
    @Operation(summary = "Busca um atendente por Email",
            description = "Realiza a busca de um atendente cadastrado por Email")
    public ResponseEntity<AtendenteDTO> findByEmail(@PathVariable String email){
        Atendente obj = this.atendenteService.findByEmail(email);
        return ResponseEntity.ok().body(new AtendenteDTO(obj));
    }
    @PostMapping
    @Operation(summary = "Criar um novo atendente",
            description = "Cria um novo atendente com base nos dados fornecidos")
    public ResponseEntity<AtendenteDTO> create(@Valid @RequestBody AtendenteDTO objDto){
        Atendente newObj = atendenteService.create(objDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
    @PutMapping(value = "/{id}")
    @Operation(summary = "Altera um atendente",
            description = "Altera um atendente existente")
    public ResponseEntity<AtendenteDTO> update(@PathVariable Long id, @Valid @RequestBody AtendenteDTO objDto){
        Atendente obj = atendenteService.update(id, objDto);
        return ResponseEntity.ok().body(new AtendenteDTO(obj));
    }
    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Deletar um atendente",
            description = "Remove um atendente a partir do seu Id")
    public ResponseEntity<AtendenteDTO> delete(@PathVariable Long id){
        atendenteService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
