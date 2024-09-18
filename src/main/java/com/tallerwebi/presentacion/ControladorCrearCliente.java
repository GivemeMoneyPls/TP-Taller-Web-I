package com.tallerwebi.presentacion;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControladorCrearCliente {


    @RequestMapping("/creacion-cliente")
    public ModelAndView irAVistaCrearCliente() {

        return new ModelAndView("creacion-cliente");
    }

    @RequestMapping(path = "/crear-cliente", method = RequestMethod.GET)
    public ModelAndView crearCLiente(String nombre, String apellido, String dni, String email, String contrasenia) {

        ModelMap modelo;

        modelo = verificacionDeCamposVacios(nombre, apellido, dni, email, contrasenia);

        if(!modelo.get("error").toString().isEmpty()){

            return new ModelAndView("creacion-cliente", modelo);
        }



        modelo.put("clienteCreado", "Se ha creado el cliente exitosamente");
        return new ModelAndView("creacion-cliente", modelo);
    }

    private ModelMap verificacionDeCamposVacios(String nombre, String apellido, String dni, String email, String contrasenia) {

        ModelMap modelo = new ModelMap();
        if (nombre.isEmpty()){
            modelo.put("error", "El nombre no puede estar vacio");
            return modelo;
        }
        if (apellido.isEmpty()){
            modelo.put("error", "El apellido no puede estar vacio");
            return modelo;
        }
        if (dni.isEmpty()){
            modelo.put("error", "El dni no puede estar vacio");
            return modelo;
        }
        if (email.isEmpty()){
            modelo.put("error", "El email no puede estar vacio");
            return modelo;
        }
        if (!email.endsWith(".com")){
            modelo.put("error", "El email debe tener un formato valido");
            return modelo;
        }
        if (contrasenia.isEmpty()){
            modelo.put("error", "La contraseña no puede estar vacia");
            return modelo;
        }
        modelo.put("error", "");

        return modelo;
    }


}
