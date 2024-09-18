package com.tallerwebi.dominio;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ServicioClienteTest {

    ServicioCliente servicioCliente = new ServicioClienteImpl();

    String NOMBRE = "Agustin";
    String APELLIDO = "Rodriguez";
    String DNI = "44669205";
    String EMAIL = "aaa@aaa.com";
    String CONTRASENIA = "a123a";

    @Test
    public void siExisteNombreApellidoDNIEmailYContraseniaLaCreacionEsExitosa(){

        //Preparacion

        //ejecucion
        Cliente clienteCreado = whenCreoCliente(NOMBRE, APELLIDO, DNI, EMAIL, CONTRASENIA);
        //comprobacion
        thenLaCreacionDeClienteEsExitosa(clienteCreado);

    }

    private Cliente whenCreoCliente(String nombre, String apellido, String dni, String email, String contrasenia) {
        Cliente clienteCreado = servicioCliente.crearCliente(nombre, apellido, dni, email, contrasenia);
        return clienteCreado;
    }

    private void thenLaCreacionDeClienteEsExitosa(Cliente clienteCreado) {
        assertThat(clienteCreado, notNullValue());
    }
}
