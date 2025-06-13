package com.atividade.resources;

import com.atividade.domains.Aluguel;
import com.atividade.domains.dtos.AluguelDTO;
import com.atividade.services.AluguelService;
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
@RequestMapping(value = "/aluguel")
@Tag(name = "Aluguel", description = "API para Gerenciamento de Alugueis")
public class AluguelResource {

    @Autowired
    private AluguelService aluguelService;

    @GetMapping
    @Operation(summary = "Listar todos os alugueis",
            description = "Retorna uma lista com todos os atendentes cadastrados")
    public ResponseEntity<List<AluguelDTO>> findAll(){
        return ResponseEntity.ok().body(aluguelService.findAll());
    }

    @GetMapping(value = "/{id}")
    @Operation(summary = "Busca um aluguel por id",
            description = "Realiza a busca de um aluguel cadastrado por id")
    public ResponseEntity<AluguelDTO> findById(@PathVariable Long id){
        Aluguel obj = this.aluguelService.findById(id);
        return ResponseEntity.ok().body(new AluguelDTO(obj));
    }

    @GetMapping(value = "/coprovantereserva/{comprovanteReserva}")
    @Operation(summary = "Busca um aluguel por Comprovante Reserva",
            description = "Realiza a busca de um aluguel cadastrado por Comprovante Reserva")
    public ResponseEntity<AluguelDTO> findByComprovanteReserva(@PathVariable String comprovanteReserva){
        Aluguel obj = this.aluguelService.findByComprovanteReserva(comprovanteReserva);
        return ResponseEntity.ok().body(new AluguelDTO(obj));
    }

    @PostMapping
    @Operation(summary = "Criar um novo aluguel",
            description = "Cria um novo aluguel com base nos dados fornecidos")
    public ResponseEntity<AluguelDTO> create(@Valid @RequestBody AluguelDTO dto){
        Aluguel Aluguel = aluguelService.create(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(Aluguel.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
    @PutMapping(value = "/{id}")
    @Operation(summary = "Altera um aluguel",
            description = "Altera um aluguel existente")
    public ResponseEntity<AluguelDTO> update(@PathVariable Long id, @Valid @RequestBody AluguelDTO objDto){
        Aluguel Obj = aluguelService.update(id, objDto);
        return ResponseEntity.ok().body(new AluguelDTO(Obj));
    }
    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Deletar um aluguel",
            description = "Remove um aluguel a partir do seu Id")
    public ResponseEntity<AluguelDTO> delete(@PathVariable Long id){
        aluguelService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
