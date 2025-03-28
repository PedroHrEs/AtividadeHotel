package com.atividade.domains;

import com.atividade.domains.dtos.VeiculoDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
@Entity
@Table(name = "veiculo")
public class Veiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_veiculo")
    private Long id;

    @NotBlank
    @NotNull
    private String descricao;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataAquisicao;
    @NotNull
    private double valorAquisicao;
    @NotNull
    @NotBlank
    private String nomeProprietario;
    @NotNull
    @NotBlank
    @Column(unique = true)
    private String cpfProprietario;

    public Veiculo() {
    }

    public Veiculo(Long id, String descricao, LocalDate dataAquisicao, double valorAquisicao, String nomeProprietario, String cpfProprietario) {
        this.id = id;
        this.descricao = descricao;
        this.dataAquisicao = dataAquisicao;
        this.valorAquisicao = valorAquisicao;
        this.nomeProprietario = nomeProprietario;
        this.cpfProprietario = cpfProprietario;
    }

    public Veiculo(VeiculoDTO obj){
        this.id = obj.getId();
        this.descricao = obj.getDescricao();
        this.dataAquisicao = obj.getDataAquisicao();
        this.valorAquisicao = obj.getValorAquisicao();
        this.nomeProprietario = obj.getNomeProprietario();
        this.cpfProprietario = obj.getCpfProprietario();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getDataAquisicao() {
        return dataAquisicao;
    }

    public void setDataAquisicao(LocalDate dataAquisicao) {
        this.dataAquisicao = dataAquisicao;
    }

    public double getValorAquisicao() {
        return valorAquisicao;
    }

    public void setValorAquisicao(double valorAquisicao) {
        this.valorAquisicao = valorAquisicao;
    }

    public String getNomeProprietario() {
        return nomeProprietario;
    }

    public void setNomeProprietario(String nomeProprietario) {
        this.nomeProprietario = nomeProprietario;
    }

    public String getCpfProprietario() {
        return cpfProprietario;
    }

    public void setCpfProprietario(String cpfProprietario) {
        this.cpfProprietario = cpfProprietario;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Veiculo veiculo)) return false;
        return Objects.equals(getId(), veiculo.getId()) && Objects.equals(getCpfProprietario(), veiculo.getCpfProprietario());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCpfProprietario());
    }
}
