package com.tallerwebi.dominio;

import com.tallerwebi.dominio.excepcion.ChoferExistente;

import java.util.List;

public interface ServicioChofer {

       void registrar(Chofer chofer) throws ChoferExistente;
       List<Chofer> listarChoferes();
}
