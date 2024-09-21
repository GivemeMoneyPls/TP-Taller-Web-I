package com.tallerwebi.infraestructura;


import com.tallerwebi.dominio.Cliente;
import com.tallerwebi.integracion.config.HibernateTestConfig;
import com.tallerwebi.integracion.config.SpringWebTestConfig;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.hamcrest.Matchers.notNullValue;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextConfiguration(classes = {SpringWebTestConfig.class, HibernateTestConfig.class})

public class RepositorioClienteTest {

    String NOMBRE = "Agustin";
    String NOMBRE_DIFERENTE = "Gonzalo";
    String APELLIDO = "Rodriguez";
    String DNI = "44669205";
    String DNI_DIFERENTE = "12345678";
    String EMAIL = "aaa@aaa.com";
    String EMAIL_DIFERENTE = "bbb@bbb.com";
    String PASSWORD = "a123a";
    Boolean ABONADO = true;

    @Autowired
    SessionFactory sessionFactory;

    @Autowired
    RepositorioClienteImpl repositorioCliente;

    @Test
    @Transactional
    @Rollback

    public void queSePuedaGuardarUnCliente(){


        //Preparacion
        Cliente clienteCreado = givenCreoYAsignoDatosAlCliente(NOMBRE, APELLIDO, DNI, EMAIL, PASSWORD, ABONADO);
        //ejecucion
        whenGuardoCliente(clienteCreado);
        //comprobacion
        thenElClienteSeGuardaExitosamente(clienteCreado);


    }

    private Cliente givenCreoYAsignoDatosAlCliente(String nombre, String apellido, String dni, String email, String password, Boolean abonado) {
        Cliente clienteCreado = new Cliente();
        clienteCreado.setNombre(nombre);
        clienteCreado.setApellido(apellido);
        clienteCreado.setDni(dni);
        clienteCreado.setEmail(email);
        clienteCreado.setPassword(password);
        clienteCreado.setAbonado(abonado);
        return clienteCreado;
    }

    private void whenGuardoCliente(Cliente cliente) {
        repositorioCliente.guardarCliente(cliente);
    }

    private void thenElClienteSeGuardaExitosamente(Cliente clienteCreado) {
        assertThat(clienteCreado.getCodigoCliente(), notNullValue());
    }

    @Test
    @Transactional
    @Rollback
    public void queSePuedaBuscarUnClientePorEmail(){


        //Preparacion
        Cliente clienteCreado = givenCreoYAsignoDatosAlCliente(NOMBRE, APELLIDO, DNI, EMAIL, PASSWORD, ABONADO);

        givenGuardoClientePorSessionFactory(clienteCreado);

        Cliente clienteCreado2 = givenCreoYAsignoDatosAlCliente(NOMBRE_DIFERENTE, APELLIDO, DNI, EMAIL_DIFERENTE, PASSWORD, ABONADO);

        givenGuardoClientePorSessionFactory(clienteCreado2);

        //ejecucion

        Cliente clienteBuscado = whenBuscoUsuarioPorEmail(clienteCreado2.getEmail());

        //comprobacion

        thenElClienteSeBuscaExitosamente(clienteBuscado.getNombre(), NOMBRE_DIFERENTE);


    }

    private void thenElClienteSeBuscaExitosamente(String datoObtenido, String datoAVerificar) {
        assertThat(datoObtenido, equalToIgnoringCase(datoAVerificar));
    }

    private Cliente whenBuscoUsuarioPorEmail(String email) {
        Cliente clienteBuscado = repositorioCliente.buscarEmail(email);
        return clienteBuscado;
    }

    private void givenGuardoClientePorSessionFactory(Cliente clienteCreado) {
        sessionFactory.getCurrentSession().save(clienteCreado);
    }

    @Test
    @Transactional
    @Rollback
    public void queSePuedaBuscarUnClientePorDni(){


        //Preparacion
        Cliente clienteCreado = givenCreoYAsignoDatosAlCliente(NOMBRE, APELLIDO, DNI, EMAIL, PASSWORD, ABONADO);

        givenGuardoClientePorSessionFactory(clienteCreado);

        Cliente clienteCreado2 = givenCreoYAsignoDatosAlCliente(NOMBRE, APELLIDO, DNI_DIFERENTE, EMAIL, PASSWORD, ABONADO);

        givenGuardoClientePorSessionFactory(clienteCreado2);

        //ejecucion

        Cliente clienteBuscado = whenBuscoUsuarioPorDni(clienteCreado2.getDni());

        //comprobacion

        thenElClienteSeBuscaExitosamente(clienteBuscado.getDni(), DNI_DIFERENTE);


    }

    private Cliente whenBuscoUsuarioPorDni(String dni) {
        Cliente clienteBuscado = repositorioCliente.buscarDni(dni);
        return clienteBuscado;
    }

}