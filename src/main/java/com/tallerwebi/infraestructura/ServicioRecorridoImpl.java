package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.Recorrido;
import com.tallerwebi.dominio.RepositorioRecorrido;
import com.tallerwebi.dominio.ServicioRecorrido;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import javax.transaction.Transactional;
import java.util.List;

@Service("servicioRecorrido")
@Transactional
public class ServicioRecorridoImpl implements ServicioRecorrido {

    private RepositorioRecorrido repositorioRecorrido;

    @Autowired
    public ServicioRecorridoImpl(RepositorioRecorrido repositorioRecorrido) {
        this.repositorioRecorrido = repositorioRecorrido;
    }

    @Override
    public void registrar(Recorrido recorrido) {
        repositorioRecorrido.guardar(recorrido);
    }

     @Override
    public List<Recorrido> listarRecorridos() {
        return repositorioRecorrido.getRecorridos();
    }


}
