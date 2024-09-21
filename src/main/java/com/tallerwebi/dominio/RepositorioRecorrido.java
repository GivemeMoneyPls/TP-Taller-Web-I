package com.tallerwebi.dominio;

import java.util.List;

public interface RepositorioRecorrido {
     void guardar(Recorrido recorrido);
     List<Recorrido> getRecorridos();
}
