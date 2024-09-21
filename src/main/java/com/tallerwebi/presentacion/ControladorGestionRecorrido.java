package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.Recorrido;
import com.tallerwebi.dominio.ServicioRecorrido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ControladorGestionRecorrido {

    private final ServicioRecorrido servicioRecorrido;

    @Autowired
    public ControladorGestionRecorrido(ServicioRecorrido servicioRecorrido) {
        this.servicioRecorrido = servicioRecorrido;
    }

    @RequestMapping("/gestion-recorrido")
    public ModelAndView mostrarFormulario() {
        ModelMap model = new ModelMap();
        List<Recorrido> recorridos = servicioRecorrido.listarRecorridos();
        model.addAttribute("recorridos", recorridos);
        return new ModelAndView("gestionRecorrido", model);
    }
}
