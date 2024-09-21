package com.tallerwebi.dominio;

public interface RepositorioCliente {

    Cliente buscarEmail(String email);

    void guardarCliente(Cliente clienteCreado);

    Cliente buscarDni(String dni);
}
