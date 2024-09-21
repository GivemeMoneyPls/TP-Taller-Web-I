package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.RepositorioChofer;
import com.tallerwebi.dominio.Chofer;
import com.tallerwebi.dominio.ServicioChofer;
import com.tallerwebi.dominio.excepcion.ChoferExistente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service("servicioChofer")
@Transactional
public class ServicioChoferImpl implements ServicioChofer {

    private RepositorioChofer repositorioChofer;

    @Autowired
    public ServicioChoferImpl(RepositorioChofer repositorioChofer) {
        this.repositorioChofer = repositorioChofer;
    }

    @Override
    public void registrar(Chofer chofer) throws ChoferExistente {
        Chofer choferEncontrado = repositorioChofer.buscarUsuario(chofer.getDni());
        if (choferEncontrado != null) {
            throw new ChoferExistente("Chofer ya existe");
        }
        repositorioChofer.guardar(chofer);
    }

    @Override
    public List<Chofer> listarChoferes() {
        return repositorioChofer.getChoferes();
    }
}
