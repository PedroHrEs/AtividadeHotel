package com.atividade.domains;

import com.atividade.domains.dtos.LocadoraDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "locadora")
public class Locadora {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_locadora")
    private Long id;

    @NotNull
    @NotBlank
    private String nomeLocadora;

    @NotNull
    @NotBlank
    @Column(unique = true)
    private String cnpj;

    @JsonIgnore
    @OneToMany(mappedBy = "locadora")
    private List<Carro> carros = new ArrayList<>();


    public Locadora() {
    }

    public Locadora(Long id, String nomeLocadora, String cnpj) {
        this.id = id;
        this.nomeLocadora = nomeLocadora;
        this.cnpj = cnpj;
    }
    public Locadora(LocadoraDTO dto) {
        this.id = dto.getId();
        this.nomeLocadora = dto.getNomeLocadora();
        this.cnpj = dto.getCnpj();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeLocadora() {
        return nomeLocadora;
    }

    public void setNomeLocadora(String nomeLocadora) {
        this.nomeLocadora = nomeLocadora;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public List<Carro> getCarros() {
        return carros;
    }

    public void setCarros(List<Carro> carros) {
        this.carros = carros;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Locadora locadora = (Locadora) o;
        return Objects.equals(id, locadora.id) && Objects.equals(cnpj, locadora.cnpj);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cnpj);
    }
}
