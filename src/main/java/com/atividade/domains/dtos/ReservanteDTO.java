package com.atividade.domains.dtos;

import com.atividade.domains.Reservante;
import com.atividade.domains.enums.TipoPessoa;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.FetchType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class ReservanteDTO {

    protected Long id;

    @NotBlank(message = "O campo nome não pode estar vazio")
    @NotNull(message = "O campo nome não pode ser nulo")
    protected String nome;

    @NotNull(message = "O campo CPF não pode ser nulo")
    @CPF
    protected String cpf;

    @NotBlank(message = "O campo email não pode estar vazio")
    @NotNull(message = "O campo email não pode ser nulo")
    protected String email;

    @NotBlank(message = "O campo Senha não pode estar vazio")
    @NotNull(message = "O campo Senha não pode ser nulo")
    protected String senha;

    @JsonFormat(pattern = "dd/MM/yyyy")
    protected LocalDate dataCriacao = LocalDate.now();

    protected Set<Integer> tipoPessoa = new HashSet<>();

    public ReservanteDTO() {
    }

    public ReservanteDTO(Reservante obj) {
        this.id = obj.getId();
        this.nome = obj.getNome();
        this.cpf = obj.getCpf();
        this.email = obj.getEmail();
        this.senha = obj.getSenha();
        this.dataCriacao = LocalDate.now();
        this.tipoPessoa.stream().map(TipoPessoa::toEnum).collect(Collectors.toSet());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank(message = "O campo nome não pode estar vazio") @NotNull(message = "O campo nome não pode ser nulo") String getNome() {
        return nome;
    }

    public void setNome(@NotBlank(message = "O campo nome não pode estar vazio") @NotNull(message = "O campo nome não pode ser nulo") String nome) {
        this.nome = nome;
    }

    public @NotNull(message = "O campo CPF não pode ser nulo") @CPF String getCpf() {
        return cpf;
    }

    public void setCpf(@NotNull(message = "O campo CPF não pode ser nulo") @CPF String cpf) {
        this.cpf = cpf;
    }

    public @NotBlank(message = "O campo email não pode estar vazio") @NotNull(message = "O campo email não pode ser nulo") String getEmail() {
        return email;
    }

    public void setEmail(@NotBlank(message = "O campo email não pode estar vazio") @NotNull(message = "O campo email não pode ser nulo") String email) {
        this.email = email;
    }

    public @NotBlank(message = "O campo Senha não pode estar vazio") @NotNull(message = "O campo Senha não pode ser nulo") String getSenha() {
        return senha;
    }

    public void setSenha(@NotBlank(message = "O campo Senha não pode estar vazio") @NotNull(message = "O campo Senha não pode ser nulo") String senha) {
        this.senha = senha;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Set<TipoPessoa> getTipoPessoa() {
        return tipoPessoa == null ? Collections.emptySet(): tipoPessoa.stream().map(TipoPessoa::toEnum).collect(Collectors.toSet());
    }

    public void addTipoPessoa(TipoPessoa tipoPessoa) {
        this.tipoPessoa.add(tipoPessoa.getId());
    }
}
