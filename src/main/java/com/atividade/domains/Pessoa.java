package com.atividade.domains;

import com.atividade.domains.enums.TipoPessoa;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "pessoa")
public abstract class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_pessoa")
    protected Long id;

    protected String nome;

    @Column(unique = true)
    protected String cpf;
    @Column(unique = true)
    protected String email;
    protected String senha;

    @JsonFormat(pattern = "dd/MM/yyyy")
    protected LocalDate dataCriacao;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "perfis")
    protected Set<Integer> tipoPessoa = new HashSet<>();

    public Pessoa() {
        addTipoPessoa(TipoPessoa.CLIENTE);
    }

    public Pessoa(Long id, String nome, String cpf, String email, String senha) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.senha = senha;
        addTipoPessoa(TipoPessoa.CLIENTE);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public  String getNome() {
        return nome;
    }

    public void setName( String nome) {
        this.nome = nome;
    }

    public  String getCpf() {
        return cpf;
    }

    public void setCpf( String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail( String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Set<TipoPessoa> getTipoPessoa() {
        return tipoPessoa.stream().map(TipoPessoa::toEnum).collect(Collectors.toSet());
    }

    public void addTipoPessoa(TipoPessoa tipoPessoa) {
        this.tipoPessoa.add(tipoPessoa.getId());
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Pessoa pessoa = (Pessoa) o;
        return Objects.equals(id, pessoa.id) && Objects.equals(cpf, pessoa.cpf);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cpf);
    }
}
