package com.atividade.domains.dtos;

import com.atividade.domains.Carro;
import com.atividade.domains.Locadora;
import com.atividade.domains.enums.Conservacao;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CarroDTO {


    private Long id;

    @NotNull(message = "O campo marca não pode ser nulo")
    @NotBlank(message="O campo marca não pode estar vazio")
    private String marca;

    @NotNull(message = "O campo modelo não pode ser nulo")
    @NotBlank(message="O campo modelo não pode estar vazio")
    private String modelo;

    @NotNull(message = "O campo placa não pode ser nulo")
    @NotBlank(message="O campo placa não pode estar vazio")
    private String placa;

    @NotNull(message = "O campo locadora não pode estar nulo")
    private Long locadora;
    private String nomeLocadora;

    private int conservacao;

    public CarroDTO() {
    }

    public CarroDTO(Carro carro) {
        this.id = carro.getId();
        this.marca = carro.getMarca();
        this.modelo = carro.getModelo();
        this.placa = carro.getPlaca();
        this.locadora = carro.getLocadora().getId();
        this.nomeLocadora = carro.getLocadora().getNomeLocadora();
        this.conservacao = conservacao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotNull(message = "O campo marca não pode ser nulo") @NotBlank(message = "O campo marca não pode estar vazio") String getMarca() {
        return marca;
    }

    public void setMarca(@NotNull(message = "O campo marca não pode ser nulo") @NotBlank(message = "O campo marca não pode estar vazio") String marca) {
        this.marca = marca;
    }

    public @NotNull(message = "O campo modelo não pode ser nulo") @NotBlank(message = "O campo modelo não pode estar vazio") String getModelo() {
        return modelo;
    }

    public void setModelo(@NotNull(message = "O campo modelo não pode ser nulo") @NotBlank(message = "O campo modelo não pode estar vazio") String modelo) {
        this.modelo = modelo;
    }

    public @NotNull(message = "O campo placa não pode ser nulo") @NotBlank(message = "O campo placa não pode estar vazio") String getPlaca() {
        return placa;
    }

    public void setPlaca(@NotNull(message = "O campo placa não pode ser nulo") @NotBlank(message = "O campo placa não pode estar vazio") String placa) {
        this.placa = placa;
    }

    public @NotNull(message = "O campo locadora não pode estar nulo") Long getLocadora() {
        return locadora;
    }

    public void setLocadora(@NotNull(message = "O campo locadora não pode estar nulo") Long locadora) {
        this.locadora = locadora;
    }

    public String getNomeLocadora() {
        return nomeLocadora;
    }

    public void setNomeLocadora(String nomeLocadora) {
        this.nomeLocadora = nomeLocadora;
    }

    public int getConservacao() {
        return conservacao;
    }

    public void setConservacao(int conservacao) {
        this.conservacao = conservacao;
    }
}
