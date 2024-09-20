package com.tallerwebi.dominio;

import java.util.List;

public interface ServicioRecorrido {
    void registrar(Recorrido recorrido);
    List<Recorrido> listarRecorridos();
}
