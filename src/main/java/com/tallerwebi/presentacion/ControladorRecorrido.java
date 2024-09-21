package com.tallerwebi.presentacion;
import com.tallerwebi.dominio.Parada;
import com.tallerwebi.dominio.Recorrido;
import com.tallerwebi.dominio.ServicioRecorrido;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

@Controller
public class ControladorRecorrido {

    private ServicioRecorrido servicioRecorrido;

    @Autowired
    public ControladorRecorrido(ServicioRecorrido servicioRecorrido) {
        this.servicioRecorrido = servicioRecorrido;
    }



    @GetMapping("/registroRecorrido")
    public String mostrarFormularioRegistro(Model model) {
        model.addAttribute("Recorrido", new Recorrido());
        return "registroRecorrido";
    }

    @RequestMapping(path = "/validar-recorrido", method = RequestMethod.POST)
    public ModelAndView validarRecorrido(@ModelAttribute("Recorrido") Recorrido recorrido) {


        String paradasInput = recorrido.getParadaInput();

        if (paradasInput != null && !paradasInput.trim().isEmpty()) {
            String[] paradasArray = paradasInput.split(",");
            List<Parada> paradasList = new ArrayList<>();

            for (String paradaNombre : paradasArray) {
                Parada parada = new Parada();
                parada.setNombre(paradaNombre.trim());
                paradasList.add(parada);
            }

            recorrido.setParadas(paradasList);

        }

        servicioRecorrido.registrar(recorrido);
        return new ModelAndView("redirect:/gestion-recorrido");

    }


}
