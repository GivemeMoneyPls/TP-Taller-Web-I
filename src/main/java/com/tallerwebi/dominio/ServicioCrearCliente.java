package com.tallerwebi.dominio;

import com.tallerwebi.dominio.excepcion.ClienteExistente;

import com.tallerwebi.dominio.excepcion.PasswordLongitudIncorrecta;

public interface ServicioCrearCliente {
    Cliente crearCliente(Cliente cliente) throws  PasswordLongitudIncorrecta, ClienteExistente;
}
