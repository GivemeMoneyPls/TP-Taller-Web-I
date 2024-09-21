package com.tallerwebi.dominio;

import java.util.List;

public interface RepositorioChofer {
      Chofer buscarUsuario(Long dni);
      void guardar(Chofer chofer);
      List<Chofer> getChoferes();
}
