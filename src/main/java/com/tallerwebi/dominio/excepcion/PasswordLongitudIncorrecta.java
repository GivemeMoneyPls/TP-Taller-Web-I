package com.tallerwebi.dominio.excepcion;

public class PasswordLongitudIncorrecta extends Exception {

    public PasswordLongitudIncorrecta(){
        super("La contraseña debe tener 5 o mas caracteres");
    }
}
