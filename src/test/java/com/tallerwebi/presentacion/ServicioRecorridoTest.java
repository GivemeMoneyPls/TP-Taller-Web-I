package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.Recorrido;
import com.tallerwebi.dominio.RepositorioRecorrido;
import com.tallerwebi.dominio.ServicioRecorrido;
import com.tallerwebi.infraestructura.ServicioRecorridoImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

public class ServicioRecorridoTest {

    @Mock
    private RepositorioRecorrido repositorioRecorrido;

    @InjectMocks
    private ServicioRecorridoImpl servicioRecorrido;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRegistrar() {
        Recorrido recorrido = new Recorrido();
        recorrido.setTitulo("Monte Grande - Capital Federal");

        servicioRecorrido.registrar(recorrido);

        verify(repositorioRecorrido, times(1)).guardar(recorrido);
    }
}
