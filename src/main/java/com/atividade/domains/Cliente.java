package com.atividade.domains;

import com.atividade.domains.dtos.ClienteDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_cliente")
    private Long id;

    @NotBlank
    @NotNull
    private String nome;

    @NotBlank
    @NotNull
    @Column(unique = true)
    private String cpf;

    @NotBlank
    @NotNull
    @Column(unique = true)
    private String cnh;

    @NotBlank
    @NotNull
    @Column(unique = true)
    private String email;

    @JsonIgnore
    @OneToMany(mappedBy = "cliente")
    private List<Aluguel> alugueis = new ArrayList<>();

    public Cliente() {
    }

    public Cliente(Long id, String nome, String cpf, String cnh, String email) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.cnh = cnh;
        this.email = email;
    }

    public Cliente(ClienteDTO dto) {
        this.id = dto.getId();
        this.nome = dto.getNome();
        this.cpf = dto.getCpf();
        this.cnh = dto.getCnh();
        this.email = dto.getEmail();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCnh() {
        return cnh;
    }

    public void setCnh(String cnh) {
        this.cnh = cnh;
    }

    public String getEmail() {
        return email;
    }

    public List<Aluguel> getAlugueis() {
        return alugueis;
    }

    public void setAlugueis(List<Aluguel> alugueis) {
        this.alugueis = alugueis;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(id, cliente.id) && Objects.equals(cpf, cliente.cpf) && Objects.equals(cnh, cliente.cnh) && Objects.equals(email, cliente.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cpf, cnh, email);
    }
}
