package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.Cliente;
import com.tallerwebi.dominio.RepositorioCliente;
import com.tallerwebi.dominio.ServicioCrearCliente;
import com.tallerwebi.dominio.excepcion.ClienteExistente;
import com.tallerwebi.dominio.excepcion.PasswordLongitudIncorrecta;
import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.ModelAndView;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ControladorCrearClienteTest {

    ServicioCrearCliente mockServicioCrearCliente = mock(ServicioCrearCliente.class);
    ControladorCrearCliente controladorCrearCliente = new ControladorCrearCliente(mockServicioCrearCliente);

    String NOMBRE = "Agustin";
    String NOMBRE_VACIO = "";
    String APELLIDO = "Rodriguez";
    String APELLIDO_VACIO = "";
    String DNI = "44669205";
    String DNI_VACIO = "";
    String EMAIL = "aaa@aaa.com";
    String EMAIL_VACIO = "";
    String EMAIL_MAL_FORMATEADO = "aaa@aaa";
    String PASSWORD = "a123a";
    String PASSWORD_VACIA = "";
    String PROVINCIA = "Buenos Aires";
    String LOCALIDAD = "San justo";
    String DOMICILIO = "Calle falsa 123";
    Integer CODIGO_POSTAL = 1754;
    Integer TELEFONO = 46930422;
    Integer TELEFONO_MOVIL = 1103268546;
    Boolean ABONADO = true;
    Boolean ABONADO_NULL = null;
    Cliente cliente = new Cliente();
    String mensajeError;


    @Test
    public void siSeRellenanTodosLosCamposNecesariosLaCreacionDeClienteEsExitosa(){

        //Preparacion
        givenAsignoDatosAlCliente(NOMBRE, APELLIDO, DNI, EMAIL, PASSWORD, ABONADO);
        //ejecucion
        ModelAndView mav = whenCreoCliente(cliente);
        //comprobacion
        thenLaCreacionDeClienteEsExitosa(mav);
    }

    private void givenAsignoDatosAlCliente(String nombre, String apellido, String dni, String email, String password, Boolean abonado) {
        cliente.setNombre(nombre);
        cliente.setApellido(apellido);
        cliente.setDni(dni);
        cliente.setEmail(email);
        cliente.setPassword(password);
        cliente.setAbonado(abonado);
    }

    private ModelAndView whenCreoCliente(Cliente cLiente) {
        ModelAndView mav = controladorCrearCliente.crearCLiente(cliente);
        return mav;
    }

    private void thenLaCreacionDeClienteEsExitosa(ModelAndView mav) {
        assertThat(mav.getViewName(),equalToIgnoringCase("gestionar-clientes"));
        assertThat(mav.getModel().get("creacionExitosa").toString(), equalToIgnoringCase("Se ha creado el cliente exitosamente"));
    }

    @Test
    public void siElNombreEstaVacioLaCreacionDeClienteFalla(){

        //Preparacion
        givenAsignoDatosAlCliente(NOMBRE_VACIO, APELLIDO, DNI, EMAIL, PASSWORD, ABONADO);
        //ejecucion
        ModelAndView mav = whenCreoCliente(cliente);
        //comprobacion
        mensajeError = "El nombre no puede estar vacio";
        thenLaCreacionDeClienteFalla(mav, mensajeError);
    }

    private void thenLaCreacionDeClienteFalla(ModelAndView mav, String mensajeError) {
        assertThat(mav.getViewName(),equalToIgnoringCase("creacion-cliente"));
        assertThat(mav.getModel().get("error").toString(), equalToIgnoringCase(mensajeError));
    }

    @Test
    public void siElApellidoEstaVacioLaCreacionDeClienteFalla(){

        //Preparacion
        givenAsignoDatosAlCliente(NOMBRE, APELLIDO_VACIO, DNI, EMAIL, PASSWORD, ABONADO);
        //ejecucion
        ModelAndView mav = whenCreoCliente(cliente);
        //comprobacion
        mensajeError = "El apellido no puede estar vacio";
        thenLaCreacionDeClienteFalla(mav, mensajeError);
    }

    @Test
    public void siElDNIEstaVacioLaCreacionDeClienteFalla(){

        //Preparacion
        givenAsignoDatosAlCliente(NOMBRE, APELLIDO, DNI_VACIO, EMAIL, PASSWORD, ABONADO);
        //ejecucion
        ModelAndView mav = whenCreoCliente(cliente);
        //comprobacion
        mensajeError = "El dni no puede estar vacio";
        thenLaCreacionDeClienteFalla(mav, mensajeError);
    }

    @Test
    public void siElEmailEstaVacioLaCreacionDeClienteFalla(){

        //Preparacion
        givenAsignoDatosAlCliente(NOMBRE, APELLIDO, DNI, EMAIL_VACIO, PASSWORD, ABONADO);
        //ejecucion
        ModelAndView mav = whenCreoCliente(cliente);
        //comprobacion
        mensajeError = "El email no puede estar vacio";
        thenLaCreacionDeClienteFalla(mav, mensajeError);
    }

    @Test
    public void siElFormatoDelEmailEstaMalLaCreacionDeClienteFalla(){

        //Preparacion
        givenAsignoDatosAlCliente(NOMBRE, APELLIDO, DNI, EMAIL_MAL_FORMATEADO, PASSWORD, ABONADO);
        //ejecucion
        ModelAndView mav = whenCreoCliente(cliente);
        //comprobacion
        mensajeError = "El email debe tener un formato valido";
        thenLaCreacionDeClienteFalla(mav, mensajeError);
    }

    @Test
    public void siLaContraseniaLEstaVaciaLaCreacionDeClienteFalla(){

        //Preparacion
        givenAsignoDatosAlCliente(NOMBRE, APELLIDO, DNI, EMAIL, PASSWORD_VACIA, ABONADO);
        //ejecucion
        ModelAndView mav = whenCreoCliente(cliente);
        //comprobacion
        mensajeError = "La contraseña no puede estar vacia";
        thenLaCreacionDeClienteFalla(mav, mensajeError);
    }

    @Test
    public void siElCampoAbonadoEsNuloLaCreacionDeClienteFalla(){

        //Preparacion
        givenAsignoDatosAlCliente(NOMBRE, APELLIDO, DNI, EMAIL, PASSWORD, ABONADO_NULL);
        //ejecucion
        ModelAndView mav = whenCreoCliente(cliente);
        //comprobacion
        mensajeError = "El campo abonado no puede ser nulo";
        thenLaCreacionDeClienteFalla(mav, mensajeError);
    }

    @Test
    public void siYaHayUnClienteRegistradoConElMismoEmailLaCreacionFalla() throws ClienteExistente, PasswordLongitudIncorrecta {

        //Preparacion
        givenAsignoDatosAlCliente(NOMBRE, APELLIDO, DNI, EMAIL, PASSWORD, ABONADO);

        ClienteExistente clienteExistente = new ClienteExistente("El mail utilizado ya fue usado para crear un cliente");

        when(mockServicioCrearCliente.crearCliente(cliente)).thenThrow(clienteExistente);

        //ejecucion
        ModelAndView mav = whenCreoCliente(cliente);
        //comprobacion
        mensajeError = "El mail utilizado ya fue usado para crear un cliente";
        thenLaCreacionDeClienteFalla(mav, mensajeError);

    }

    @Test
    public void siYaHayUnClienteRegistradoConElMismoDnilLaCreacionFalla() throws ClienteExistente, PasswordLongitudIncorrecta {

        //Preparacion
        givenAsignoDatosAlCliente(NOMBRE, APELLIDO, DNI, EMAIL, PASSWORD, ABONADO);

        ClienteExistente clienteExistente = new ClienteExistente("El dni utilizado ya fue usado para crear un cliente");

        when(mockServicioCrearCliente.crearCliente(cliente)).thenThrow(clienteExistente);

        //ejecucion
        ModelAndView mav = whenCreoCliente(cliente);
        //comprobacion
        mensajeError = "El dni utilizado ya fue usado para crear un cliente";
        thenLaCreacionDeClienteFalla(mav, mensajeError);

    }



}
