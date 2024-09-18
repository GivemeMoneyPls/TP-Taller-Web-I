package com.tallerwebi.presentacion;

import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.ModelAndView;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ControladorCrearClienteTest {


    private ControladorCrearCliente controladorCrearCliente = new ControladorCrearCliente();
    String NOMBRE = "Agustin";
    String NOMBRE_VACIO = "";
    String APELLIDO = "Rodriguez";
    String APELLIDO_VACIO = "";
    String DNI = "44669205";
    String DNI_VACIO = "";
    String EMAIL = "aaa@aaa.com";
    String EMAIL_VACIO = "";
    String EMAIL_MAL_FORMATEADO = "aaa@aaa";
    String PROVINCIA = "Buenos Aires";
    String LOCALIDAD = "San justo";
    String DOMICILIO = "Calle falsa 123";
    Integer CODIGO_POSTAL = 1754;
    Integer TELEFONO = 46930422;
    Integer TELEFONO_MOVIL = 1103268546;
    Integer CODIGO_CLIENTE = 1234;
    String CONTRASENIA = "a123a";
    String CONTRASENIA_VACIA = "";
    String mensajeError;


    @Test
    public void siSeRellenanTodosLosCamposNecesariosLaCreacionDeClienteEsExitosa(){

        //Preparacion
        givenNoExisteUsuario();
        //ejecucion
        ModelAndView mav = whenCreoCliente(NOMBRE, APELLIDO, DNI, EMAIL, CONTRASENIA);
        //comprobacion
        thenLaCreacionDeClienteEsExitosa(mav);
    }

    private void givenNoExisteUsuario() {

    }

    private ModelAndView whenCreoCliente(String nombre, String apellido, String dni, String email, String contrasenia) {
        ModelAndView mav = controladorCrearCliente.crearCLiente(nombre, apellido, dni, email, contrasenia);
        return mav;
    }

    private void thenLaCreacionDeClienteEsExitosa(ModelAndView mav) {
        assertThat(mav.getViewName(),equalToIgnoringCase("creacion-cliente"));
        assertThat(mav.getModel().get("clienteCreado").toString(), equalToIgnoringCase("Se ha creado el cliente exitosamente"));
    }

    @Test
    public void siElNombreEstaVacioLaCreacionDeClienteFalla(){

        //Preparacion
        givenNoExisteUsuario();
        //ejecucion
        ModelAndView mav = whenCreoCliente(NOMBRE_VACIO, APELLIDO, DNI, EMAIL, CONTRASENIA);
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
        givenNoExisteUsuario();
        //ejecucion
        ModelAndView mav = whenCreoCliente(NOMBRE, APELLIDO_VACIO, DNI, EMAIL, CONTRASENIA);
        //comprobacion
        mensajeError = "El apellido no puede estar vacio";
        thenLaCreacionDeClienteFalla(mav, mensajeError);
    }

    @Test
    public void siElDNIEstaVacioLaCreacionDeClienteFalla(){

        //Preparacion
        givenNoExisteUsuario();
        //ejecucion
        ModelAndView mav = whenCreoCliente(NOMBRE, APELLIDO, DNI_VACIO, EMAIL, CONTRASENIA);
        //comprobacion
        mensajeError = "El dni no puede estar vacio";
        thenLaCreacionDeClienteFalla(mav, mensajeError);
    }

    @Test
    public void siElEmailEstaVacioLaCreacionDeClienteFalla(){

        //Preparacion
        givenNoExisteUsuario();
        //ejecucion
        ModelAndView mav = whenCreoCliente(NOMBRE, APELLIDO, DNI, EMAIL_VACIO, CONTRASENIA);
        //comprobacion
        mensajeError = "El email no puede estar vacio";
        thenLaCreacionDeClienteFalla(mav, mensajeError);
    }

    @Test
    public void siElFormatoDelEmailEstaMalLaCreacionDeClienteFalla(){

        //Preparacion
        givenNoExisteUsuario();
        //ejecucion
        ModelAndView mav = whenCreoCliente(NOMBRE, APELLIDO, DNI, EMAIL_MAL_FORMATEADO, CONTRASENIA);
        //comprobacion
        mensajeError = "El email debe tener un formato valido";
        thenLaCreacionDeClienteFalla(mav, mensajeError);
    }

    @Test
    public void siLaContraseniaLEstaVaciaLaCreacionDeClienteFalla(){

        //Preparacion
        givenNoExisteUsuario();
        //ejecucion
        ModelAndView mav = whenCreoCliente(NOMBRE, APELLIDO, DNI, EMAIL, CONTRASENIA_VACIA);
        //comprobacion
        mensajeError = "La contraseña no puede estar vacia";
        thenLaCreacionDeClienteFalla(mav, mensajeError);
    }
}
