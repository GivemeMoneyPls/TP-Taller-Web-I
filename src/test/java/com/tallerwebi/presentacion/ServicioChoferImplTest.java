package com.tallerwebi.presentacion;
import static org.mockito.Mockito.*;

import com.tallerwebi.dominio.Chofer;
import com.tallerwebi.dominio.RepositorioChofer;
import com.tallerwebi.dominio.excepcion.ChoferExistente;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.tallerwebi.infraestructura.ServicioChoferImpl;

public class ServicioChoferImplTest {

    @Mock
    private RepositorioChofer repositorioChofer;

    @InjectMocks
    private ServicioChoferImpl servicioChofer;

    @BeforeEach
    public void setUp() {

        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void queSePuedaRegistrarChoferExitoso() throws Exception {
        Chofer chofer = new Chofer();
        chofer.setDni(12345678L);
        chofer.setNombre("Juan");
        chofer.setApellido("Perez");

        when(repositorioChofer.buscarUsuario(chofer.getDni())).thenReturn(null); // No existe chofer

        servicioChofer.registrar(chofer);

        verify(repositorioChofer, times(1)).guardar(chofer);
    }

    @Test
    public void queSePuedaRegistrarChoferExistente() throws Exception {
        Chofer chofer = new Chofer();
        chofer.setDni(12345678L);
        chofer.setNombre("Juan");
        chofer.setApellido("Perez");

        when(repositorioChofer.buscarUsuario(chofer.getDni())).thenReturn(chofer); // Chofer ya existe

        try {
            servicioChofer.registrar(chofer);
        } catch (ChoferExistente e) {
            // Verifica que se lanzó la excepción
            assert e.getMessage().equals("Chofer ya existe");
        }

        verify(repositorioChofer, never()).guardar(chofer);
    }
}
