package com.tallerwebi.dominio;

public interface RepositorioChofer {
      Chofer buscarUsuario(Long dni);
       void guardar(Chofer chofer);
}
