package com.tallerwebi.dominio;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ServicioClienteImpl implements ServicioCliente {


    @Override
    public void crearCliente(String nombre, String apellido, String dni, String email, String contrasenia) {

    }

}
