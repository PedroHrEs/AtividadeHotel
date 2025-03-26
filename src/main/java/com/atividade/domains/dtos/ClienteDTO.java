package com.atividade.domains.dtos;

import com.atividade.domains.Cliente;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ClienteDTO {


    private Long id;

    @NotBlank(message = "O campo nome não pode ser vazio")
    @NotNull(message = "O campo nome não pode ser nulo")
    private String nome;

    @NotBlank(message = "O campo cpf não pode ser vazio")
    @NotNull(message = "O campo cpf não pode ser nulo")
    private String cpf;

    @NotBlank(message = "O campo cnh não pode ser vazio")
    @NotNull(message = "O campo cnh não pode ser nulo")
    private String cnh;

    @NotBlank(message = "O campo email não pode ser vazio")
    @NotNull(message = "O campo email não pode ser nulo")
    private String email;


    public ClienteDTO() {
    }

    public ClienteDTO(Cliente cliente) {
        this.id = cliente.getId();
        this.nome = cliente.getNome();
        this.cpf = cliente.getCpf();
        this.cnh = cliente.getCnh();
        this.email = cliente.getEmail();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank(message = "O campo nome não pode ser vazio") @NotNull(message = "O campo nome não pode ser nulo") String getNome() {
        return nome;
    }

    public void setNome(@NotBlank(message = "O campo nome não pode ser vazio") @NotNull(message = "O campo nome não pode ser nulo") String nome) {
        this.nome = nome;
    }

    public @NotBlank(message = "O campo cpf não pode ser vazio") @NotNull(message = "O campo cpf não pode ser nulo") String getCpf() {
        return cpf;
    }

    public void setCpf(@NotBlank(message = "O campo cpf não pode ser vazio") @NotNull(message = "O campo cpf não pode ser nulo") String cpf) {
        this.cpf = cpf;
    }

    public @NotBlank(message = "O campo cnh não pode ser vazio") @NotNull(message = "O campo cnh não pode ser nulo") String getCnh() {
        return cnh;
    }

    public void setCnh(@NotBlank(message = "O campo cnh não pode ser vazio") @NotNull(message = "O campo cnh não pode ser nulo") String cnh) {
        this.cnh = cnh;
    }

    public @NotBlank(message = "O campo email não pode ser vazio") @NotNull(message = "O campo email não pode ser nulo") String getEmail() {
        return email;
    }

    public void setEmail(@NotBlank(message = "O campo email não pode ser vazio") @NotNull(message = "O campo email não pode ser nulo") String email) {
        this.email = email;
    }
}
