package com.tallerwebi.integracion;

import com.tallerwebi.dominio.Recorrido;
import com.tallerwebi.dominio.ServicioRecorrido;
import com.tallerwebi.integracion.config.HibernateTestConfig;
import com.tallerwebi.integracion.config.SpringWebTestConfig;
import com.tallerwebi.presentacion.ControladorGestionRecorrido;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import java.util.Arrays;
import java.util.List;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextConfiguration(classes = {SpringWebTestConfig.class, HibernateTestConfig.class})
public class ControladorGestionRecorridoTest {

     @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Mock
    private ServicioRecorrido servicioRecorrido;

    private ControladorGestionRecorrido controladorGestionRecorrido;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        controladorGestionRecorrido = new ControladorGestionRecorrido(servicioRecorrido);
        mockMvc = MockMvcBuilders.standaloneSetup(controladorGestionRecorrido).build();
    }

    @Test

    public void testMostrarFormulario() throws Exception {

        Recorrido recorrido1 = new Recorrido();
        recorrido1.setTitulo("Recorrido 1");
        recorrido1.setSalida("Lugar A");
        recorrido1.setDestino("Lugar B");

        Recorrido recorrido2 = new Recorrido();
        recorrido2.setTitulo("Recorrido 2");
        recorrido2.setSalida("Lugar C");
        recorrido2.setDestino("Lugar D");

        List<Recorrido> recorridosMock = Arrays.asList(recorrido1, recorrido2);
        Mockito.when(servicioRecorrido.listarRecorridos()).thenReturn(recorridosMock);

        mockMvc.perform(MockMvcRequestBuilders.get("/gestion-recorrido"))
            .andExpect(MockMvcResultMatchers.view().name("gestionRecorrido"))
            .andExpect(MockMvcResultMatchers.model().attributeExists("recorridos"))
            .andExpect(MockMvcResultMatchers.model().attribute("recorridos", recorridosMock));
}



}