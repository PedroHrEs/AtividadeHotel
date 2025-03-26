package com.atividade.domains.enums;

public enum TipoQuarto {

    CASAL(0,"CASAL"), UMAPESSOA(1,"UMA_PESSOA"), DUASPESSOAS(2,"DUAS_PESSOAS");

    private Integer id;
    private String tipoQuarto;

    TipoQuarto(Integer id, String tipoQuarto) {
        this.id = id;
        this.tipoQuarto = tipoQuarto;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTipoQuarto() {
        return tipoQuarto;
    }

    public void setTipoQuarto(String tipoQuarto) {
        this.tipoQuarto = tipoQuarto;
    }

    public static TipoQuarto toEnum(Integer id){
        if(id==null) return null;
        for(TipoQuarto x: TipoQuarto.values()) {
            return x;
        }
        throw new IllegalArgumentException("Tipo do Quarto Inválido!");
    }
}
