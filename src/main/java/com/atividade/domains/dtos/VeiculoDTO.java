package com.atividade.domains.dtos;

import com.atividade.domains.Veiculo;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public class VeiculoDTO {

    private Long id;
    @NotBlank(message="O campo descricao não pode ser vazio")
    @NotNull(message="O campo descricao não pode ser nulo")
    private String descricao;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataAquisicao;

    @NotNull(message="O campo Valor aquisição não pode ser nulo")

    private double valorAquisicao;
    @NotBlank(message="O campo Nome do proprietario não pode ser vazio")
    @NotNull(message="O campo Nome do proprietaro não pode ser nulo")
    private String nomeProprietario;
    @NotBlank(message="O campo cpf do proprietario não pode ser vazio")
    @NotNull(message="O campo cpf do proprietario não pode ser nulo")
    private String cpfProprietario;

    public VeiculoDTO() {
    }
    public VeiculoDTO(Veiculo veiculo){
        this.id = veiculo.getId();
        this.descricao = veiculo.getDescricao();
        this.dataAquisicao = veiculo.getDataAquisicao();
        this.valorAquisicao = veiculo.getValorAquisicao();
        this.nomeProprietario = veiculo.getNomeProprietario();
        this.cpfProprietario = veiculo.getCpfProprietario();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank(message="O campo descricao não pode ser vazio") @NotNull(message="O campo descricao não pode ser nulo")String getDescricao() {
        return descricao;
    }

    public void setDescricao(@NotBlank(message="O campo descricao não pode ser vazio") @NotNull(message="O campo descricao não pode ser nulo")String descricao) {
        this.descricao = descricao;
    }

    public @JsonFormat(pattern = "dd/MM/yyyy") LocalDate getDataAquisicao() {
        return dataAquisicao;
    }

    public void setDataAquisicao(@JsonFormat(pattern = "dd/MM/yyyy") LocalDate dataAquisicao) {
        this.dataAquisicao = dataAquisicao;
    }

    public @NotNull(message="O campo Valor aquisição não pode ser nulo") double getValorAquisicao() {
        return valorAquisicao;
    }

    public void setValorAquisicao(@NotNull(message="O campo Valor aquisição não pode ser nulo") double valorAquisicao) {
        this.valorAquisicao = valorAquisicao;
    }

    public @NotBlank(message="O campo Nome do proprietario não pode ser vazio") @NotNull(message="O campo Nome do proprietaro não pode ser nulo") String getNomeProprietario() {
        return nomeProprietario;
    }

    public void setNomeProprietario(@NotBlank(message="O campo Nome do proprietario não pode ser vazio") @NotNull(message="O campo Nome do proprietaro não pode ser nulo")String nomeProprietario) {
        this.nomeProprietario = nomeProprietario;
    }

    public  @NotBlank(message="O campo cpf do proprietario não pode ser vazio") @NotNull(message="O campo cpf do proprietario não pode ser nulo") String getCpfProprietario() {
        return cpfProprietario;
    }

    public void setCpfProprietario( @NotBlank(message="O campo cpf do proprietario não pode ser vazio") @NotNull(message="O campo cpf do proprietario não pode ser nulo") String cpfProprietario) {
        this.cpfProprietario = cpfProprietario;
    }
}
