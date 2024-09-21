package com.tallerwebi.dominio;

import com.tallerwebi.dominio.excepcion.ClienteExistente;
import com.tallerwebi.dominio.excepcion.PasswordLongitudIncorrecta;
import com.tallerwebi.infraestructura.ServicioClienteImpl;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class ServicioCrearClienteTest {

    RepositorioCliente mockRepositorioCliente = mock(RepositorioCliente.class);
    ServicioCrearCliente servicioCliente = new ServicioClienteImpl(mockRepositorioCliente);

    Cliente cliente = new Cliente();
    Cliente clienteDuplicado = new Cliente();

    String NOMBRE = "Agustin";
    String APELLIDO = "Rodriguez";
    String DNI = "44669205";
    String EMAIL = "aaa@aaa.com";
    String PASSWORD = "a123a";
    String PASSWORD_CORTA = "a12a";
    String PROVINCIA = "Buenos Aires";
    String LOCALIDAD = "San justo";
    String DOMICILIO = "Calle falsa 123";
    Integer CODIGO_POSTAL = 1754;
    Integer TELEFONO = 46930422;
    Integer TELEFONO_MOVIL = 1103268546;
    Integer CODIGO_CLIENTE = 1234;
    Boolean ABONADO = true;


    @Test
    public void siExisteNombreApellidoDNIEmailPasswordYAbonadoLaCreacionEsExitosa() throws PasswordLongitudIncorrecta, ClienteExistente {

        //Preparacion
        givenAsignoDatosAlCliente(NOMBRE, APELLIDO, DNI, EMAIL, PASSWORD, ABONADO);
        //ejecucion
        Cliente clienteCreado = whenCreoCliente(cliente);
        //comprobacion
        thenLaCreacionDeClienteEsExitosa(clienteCreado);

    }

    private void givenAsignoDatosAlCliente(String nombre, String apellido, String dni, String email, String password, Boolean abonado) {
        cliente.setNombre(nombre);
        cliente.setApellido(apellido);
        cliente.setDni(dni);
        cliente.setEmail(email);
        cliente.setPassword(password);
        cliente.setAbonado(abonado);
    }

    private Cliente whenCreoCliente(Cliente cliente) throws PasswordLongitudIncorrecta, ClienteExistente {
        Cliente clienteCreado = servicioCliente.crearCliente(cliente);
        return clienteCreado;
    }

    private void thenLaCreacionDeClienteEsExitosa(Cliente clienteCreado) {
        assertThat(clienteCreado, notNullValue());
        verify(mockRepositorioCliente, times(1)).guardarCliente(clienteCreado);
    }

    @Test
    public void siLaContraseniaNoCumpleLosRequisitosDeLongitudLaCreacionDeClienteFalla(){

        //Preparacion
        givenAsignoDatosAlCliente(NOMBRE, APELLIDO, DNI, EMAIL, PASSWORD_CORTA, ABONADO);
        //ejecucion

        //comprobacion
        assertThrows(PasswordLongitudIncorrecta.class, ()-> whenCreoCliente(cliente));


    }

    private void thenLaCreacionDeClienteFalla(Cliente clienteCreado) {
    }

    @Test
    public void siElEmailYafueUtilizadoLaCreacionDeClienteFalla() throws ClienteExistente, PasswordLongitudIncorrecta {

        //Preparacion
        givenAsignoDatosAlCliente(NOMBRE, APELLIDO, DNI, EMAIL, PASSWORD, ABONADO);
        //ejecucion
        when(mockRepositorioCliente.buscarEmail(cliente.getEmail())).thenReturn(new Cliente());
        //comprobacion
        assertThrows(ClienteExistente.class, ()-> whenCreoCliente(cliente));


    }

    @Test
    public void siElDNIYafueUtilizadoLaCreacionDeClienteFalla() throws ClienteExistente, PasswordLongitudIncorrecta {

        //Preparacion
        givenAsignoDatosAlCliente(NOMBRE, APELLIDO, DNI, EMAIL, PASSWORD, ABONADO);
        //ejecucion
        when(mockRepositorioCliente.buscarDni(cliente.getDni())).thenReturn(new Cliente());
        //comprobacion
        assertThrows(ClienteExistente.class, ()-> whenCreoCliente(cliente));


    }


}
