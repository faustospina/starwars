package com.porvenirms.starwars.exception;


public class PorvenirBussinesException extends Exception{

    private final int codigo;
    private final String mensaje;
    private final int status;

    public PorvenirBussinesException(int codigo, String mensaje, int status) {
        super(mensaje);
        this.codigo = codigo;
        this.mensaje = mensaje;
        this.status = status;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public int getStatus() {
        return status;
    }

}
