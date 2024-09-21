package com.tallerwebi.integracion;
import com.tallerwebi.dominio.Chofer;
import com.tallerwebi.integracion.config.HibernateTestConfig;
import com.tallerwebi.integracion.config.SpringWebTestConfig;
import com.tallerwebi.presentacion.ControladorGestionChofer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import java.util.Arrays;
import java.util.List;
import com.tallerwebi.dominio.ServicioChofer;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextConfiguration(classes = {SpringWebTestConfig.class, HibernateTestConfig.class})
public class ControladorGestionChoferTest {


    @Autowired
    private WebApplicationContext wac;
    private MockMvc mockMvc;

    @Mock
    private ServicioChofer servicioChofer;

    @InjectMocks
    private ControladorGestionChofer controladorGestionChofer;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this); // Inicializar los mocks
        this.mockMvc = MockMvcBuilders.standaloneSetup(controladorGestionChofer).build();
    }

    @Test
    public void queSeMuestreLaVistaGestionChofer() throws Exception {
        mockMvc.perform(get("/gestion-choferes"))
               .andExpect(status().isOk())
               .andExpect(view().name("gestionChofer"));
    }

    @Test
    public void queSePuedaMostrarLosChoferesRegistrados() throws Exception {
        Chofer chofer1 = new Chofer();
        Chofer chofer2 = new Chofer();
        List<Chofer> choferesMock = Arrays.asList(chofer1, chofer2);


        given(servicioChofer.listarChoferes()).willReturn(choferesMock);

        mockMvc.perform(get("/gestion-choferes"))
               .andExpect(status().isOk())
               .andExpect(view().name("gestionChofer"))
               .andExpect(model().attributeExists("choferes"))
               .andExpect(model().attribute("choferes", choferesMock));
    }


}
