package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.Chofer;
import com.tallerwebi.dominio.ServicioChofer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.ModelMap;
import java.util.List;

@Controller
public class ControladorGestionChofer {

    ServicioChofer servicioChofer;

    @Autowired
    public ControladorGestionChofer(ServicioChofer servicioChofer) {
        this.servicioChofer = servicioChofer;
    }

    @RequestMapping("/gestion-choferes")
    public ModelAndView mostrarFormularioChofer(){
        ModelMap model = new ModelMap();
        List<Chofer> choferes = servicioChofer.listarChoferes();
        model.addAttribute("choferes", choferes);
        return new ModelAndView("gestionChofer",model);
    }

    @RequestMapping("/ver-home")
    public ModelAndView irAlHome() {

         return new ModelAndView("home");

    }
}
