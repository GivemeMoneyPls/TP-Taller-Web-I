package com.tallerwebi.dominio;

import com.tallerwebi.dominio.excepcion.ChoferExistente;

public interface ServicioChofer {

       void registrar(Chofer chofer) throws ChoferExistente;
}
