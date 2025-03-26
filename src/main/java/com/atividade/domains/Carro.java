package com.atividade.domains;

import com.atividade.domains.dtos.CarroDTO;
import com.atividade.domains.enums.Conservacao;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="carro")
public class Carro {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_carro")
    private Long id;

    @NotNull
    @NotBlank
    private String marca;

    @NotNull
    @NotBlank
    private String modelo;

    @NotNull
    @NotBlank
    @Column(unique = true)
    private String placa;

    @ManyToOne
    @JoinColumn(name= "idcarro")
    private Locadora locadora;

    @Enumerated(EnumType.ORDINAL)
    @JoinColumn(name = "conservacao")
    private Conservacao conservacao;

    @JsonIgnore
    @OneToMany(mappedBy = "carro")
    private List<Aluguel> alugueis = new ArrayList<>();


    public Carro() {
        this.conservacao = Conservacao.NOVO;
    }

    public Carro(Long id, String marca, String modelo, String placa,Locadora locadora, Conservacao conservacao) {
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.placa = placa;
        this.conservacao = conservacao;
        this.locadora = locadora;
    }

    public Carro(CarroDTO dto){
        this.id = dto.getId();
        this.marca = dto.getMarca();
        this.modelo = dto.getModelo();
        this.placa = dto.getPlaca();
        this.conservacao = Conservacao.toEnum(dto.getConservacao());
        this.locadora = new Locadora();
        this.locadora.setId(dto.getLocadora());

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public Conservacao getConservacao() {
        return conservacao;
    }

    public void setConservacao(Conservacao conservacao) {
        this.conservacao = conservacao;
    }

    public Locadora getLocadora() {
        return locadora;
    }

    public void setLocadora(Locadora locadora) {
        this.locadora = locadora;
    }

    public List<Aluguel> getAlugueis() {
        return alugueis;
    }

    public void setAlugueis(List<Aluguel> alugueis) {
        this.alugueis = alugueis;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Carro carro = (Carro) o;
        return Objects.equals(id, carro.id) && Objects.equals(placa, carro.placa);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, placa);
    }
}
