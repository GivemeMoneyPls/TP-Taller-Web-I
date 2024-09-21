package com.tallerwebi.dominio.excepcion;

public class ClienteExistente extends Exception {


    public ClienteExistente(String msj) {
        super(msj);
    }
}
