package com.tallerwebi.integracion;

import com.tallerwebi.dominio.Recorrido;
import com.tallerwebi.dominio.ServicioRecorrido;
import com.tallerwebi.integracion.config.HibernateTestConfig;
import com.tallerwebi.integracion.config.SpringWebTestConfig;
import com.tallerwebi.presentacion.ControladorRecorrido;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextConfiguration(classes = {SpringWebTestConfig.class, HibernateTestConfig.class})
public class ControladorRecorridoTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    private ServicioRecorrido servicioRecorrido;

    private ControladorRecorrido controladorRecorrido;

    @BeforeEach
    public void setup() {
        servicioRecorrido = Mockito.mock(ServicioRecorrido.class);
        controladorRecorrido = new ControladorRecorrido(servicioRecorrido);
        mockMvc = MockMvcBuilders.standaloneSetup(controladorRecorrido).build();
    }

    @Test
    public void queSePuedaValidarRecorrido() throws Exception {
        String paradasInput = "Parada1, Parada2, Parada3";

        mockMvc.perform(post("/validar-recorrido")
                .param("paradaInput", paradasInput)
                .flashAttr("recorrido", new Recorrido()))  // Cambiado a "recorrido"
                .andExpect(view().name("redirect:/gestion-recorrido"));
    }
}
