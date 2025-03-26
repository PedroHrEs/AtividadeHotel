package com.atividade.domains.enums;

public enum StatusReserva {

    CHECKIN(0, "CHECKIN"), CHECKOUT(1, "CHECKOUT"), CANCELADO(2, "CANCELADO"), RESERVADO(3, "RESERVADO");

    private Integer id;
    private String statusReserva;

    StatusReserva(Integer id, String statusReserva) {
        this.id = id;
        this.statusReserva = statusReserva;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStatusReserva() {
        return statusReserva;
    }

    public void setStatusReserva(String statusReserva) {
        this.statusReserva = statusReserva;
    }
    public static StatusReserva toEnum(Integer id){
        if(id==null) return null;
        for(StatusReserva x: StatusReserva.values()) {
            return x;
        }
    throw new IllegalArgumentException("Status da reserva Inválido!");
    }

}

