package com.atividade.domains.dtos;


import com.atividade.domains.Locadora;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class LocadoraDTO {

    private Long id;

    @NotNull(message = "O campo Nome Locadora não pode estar nulo")
    @NotBlank(message = "O campo Nome Locadora não pode estar vazio")
    private String nomeLocadora;

    @NotNull(message = "O campo CNPJ não pode estar nulo")
    @NotBlank(message = "O campo CNPJ não pode estar vazio")
    private String cnpj;

    public LocadoraDTO() {
    }

    public LocadoraDTO(Locadora locadora) {
        this.id = locadora.getId();
        this.nomeLocadora = locadora.getNomeLocadora();
        this.cnpj = locadora.getCnpj();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotNull(message = "O campo Nome Locadora não pode estar nulo") @NotBlank(message = "O campo Nome Locadora não pode estar vazio") String getNomeLocadora() {
        return nomeLocadora;
    }

    public void setNomeLocadora(@NotNull(message = "O campo Nome Locadora não pode estar nulo") @NotBlank(message = "O campo Nome Locadora não pode estar vazio") String nomeLocadora) {
        this.nomeLocadora = nomeLocadora;
    }

    public @NotNull(message = "O campo CNPJ não pode estar nulo") @NotBlank(message = "O campo CNPJ não pode estar vazio") String getCnpj() {
        return cnpj;
    }

    public void setCnpj(@NotNull(message = "O campo CNPJ não pode estar nulo") @NotBlank(message = "O campo CNPJ não pode estar vazio") String cnpj) {
        this.cnpj = cnpj;
    }
}
