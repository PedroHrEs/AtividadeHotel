package com.atividade.resources;


import com.atividade.domains.Veiculo;
import com.atividade.domains.dtos.VeiculoDTO;
import com.atividade.services.ClienteService;
import com.atividade.services.VeiculoService;
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
@RequestMapping(value = "/veiculo")
@Tag(name = "Veiculo", description = "API para Gerenciamento de Veiculos")
public class VeiculoResource {
    @Autowired
    private VeiculoService veiculoService;

    @GetMapping
    @Operation(summary = "Listar todos os Veiculos",
            description = "Retorna uma lista com todos os veiculos cadastrados")
    public ResponseEntity<List<VeiculoDTO>> findAll() {
        return ResponseEntity.ok().body(veiculoService.findAll());
    }

    @GetMapping(value = "/{id}")
    @Operation(summary = "Busca um veiculo por id",
            description = "Realiza a busca de um veiculo cadastrado por id")
    public ResponseEntity<VeiculoDTO> findById(@PathVariable Long id) {
        Veiculo obj = this.veiculoService.findById(id);
        return ResponseEntity.ok().body(new VeiculoDTO(obj));
    }

    @GetMapping(value = "/cpfproprietario/{cpfproprietario}")
    @Operation(summary = "Busca um veiculo por CPF do proprietario",
            description = "Realiza a busca de um veiculo cadastrado por CPF do proprietario")
    public ResponseEntity<VeiculoDTO> findByCpfProprietario(@PathVariable String cpfProprietario) {
        Veiculo obj = this.veiculoService.findByCpfProprietario(cpfProprietario);
        return ResponseEntity.ok().body(new VeiculoDTO(obj));
    }

    @PostMapping
    @Operation(summary = "Criar um novo veiculo",
            description = "Cria um novo veiculo com base nos dados fornecidos")
    public ResponseEntity<VeiculoDTO> create(@Valid @RequestBody VeiculoDTO dto) {
        Veiculo veiculo = veiculoService.create(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(veiculo.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    @Operation(summary = "Altera um veiculo",
            description = "Altera um veiculo existente")
    public ResponseEntity<VeiculoDTO> update(@PathVariable Long id, @Valid @RequestBody VeiculoDTO objDto) {
        Veiculo Obj = veiculoService.update(id, objDto);
        return ResponseEntity.ok().body(new VeiculoDTO(Obj));
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Deletar um veiculo",
            description = "Remove um veiculo a partir do seu Id")
    public ResponseEntity<VeiculoDTO> delete(@PathVariable Long id) {
        veiculoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
