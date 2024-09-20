package com.tallerwebi.presentacion;
import com.tallerwebi.dominio.Chofer;
import com.tallerwebi.dominio.ServicioChofer;
import com.tallerwebi.dominio.excepcion.ChoferExistente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;



@Controller
public class ControladorChofer {

    private ServicioChofer servicioChofer;

    @Autowired
    public ControladorChofer(ServicioChofer servicioChofer){
        this.servicioChofer = servicioChofer;
    }

    @RequestMapping("/registroChofer")
    public ModelAndView mostrarFormularioRegistro() {
        ModelMap modelo = new ModelMap();
        modelo.put("Chofer", new Chofer());
        return new ModelAndView("registroChofer", modelo);
    }


    @RequestMapping(path = "/validar-registro_chofer", method = RequestMethod.POST)
public ModelAndView validarChofer(@ModelAttribute("Chofer") Chofer chofer) {
    ModelMap model = new ModelMap();

    try {
        servicioChofer.registrar(chofer);

    } catch (ChoferExistente e) {
        model.put("error", "El chofer ya existe");
        return new ModelAndView("registroChofer", model);
    } catch (Exception e) {
        model.put("error", "Error al registrar el nuevo usuario");
        return new ModelAndView("registroChofer", model);
    }

    return new ModelAndView("home"); // Asegúrate de redirigir
}
}