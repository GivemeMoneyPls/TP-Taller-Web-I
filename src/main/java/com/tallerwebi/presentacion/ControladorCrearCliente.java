package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.Cliente;
import com.tallerwebi.dominio.ServicioCrearCliente;
import com.tallerwebi.dominio.excepcion.ClienteExistente;
import com.tallerwebi.dominio.excepcion.PasswordLongitudIncorrecta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControladorCrearCliente {

    ServicioCrearCliente servicioCrearCliente;

    @Autowired
    public ControladorCrearCliente(ServicioCrearCliente servicioCrearCliente){
        this.servicioCrearCliente = servicioCrearCliente;
    }


    @RequestMapping("/creacion-cliente")
    public ModelAndView irAVistaCrearCliente() {
        ModelMap modelo = new ModelMap();
        modelo.put("Cliente", new Cliente());
        return new ModelAndView("creacion-cliente", modelo);
    }

    @RequestMapping(path = "/crear-cliente", method = RequestMethod.POST)
    public ModelAndView crearCLiente(@ModelAttribute("Cliente") Cliente cliente) {

        Cliente clienteCreado;
        ModelMap modelo;

        modelo = verificacionDeCamposVacios(cliente);

        if(!modelo.get("error").toString().isEmpty()){

            return new ModelAndView("creacion-cliente", modelo);
        }

        try {
            clienteCreado = servicioCrearCliente.crearCliente(cliente);
        } catch (ClienteExistente | PasswordLongitudIncorrecta e) {
            String mensajeError = e.getMessage();
            modelo.put("error", mensajeError);
            return new ModelAndView("creacion-cliente", modelo);
        }


        modelo.put("creacionExitosa", "Se ha creado el cliente exitosamente");
        modelo.put("clienteCreado", clienteCreado);
        return new ModelAndView("gestionar-clientes", modelo);
    }

    private ModelMap verificacionDeCamposVacios(Cliente cliente) {

        ModelMap modelo = new ModelMap();

        if (cliente.getNombre().isEmpty()){
            modelo.put("error", "El nombre no puede estar vacio");
            return modelo;
        }
        if (cliente.getApellido().isEmpty()){
            modelo.put("error", "El apellido no puede estar vacio");
            return modelo;
        }
        if (cliente.getDni().isEmpty()){
            modelo.put("error", "El dni no puede estar vacio");
            return modelo;
        }
        if (cliente.getEmail().isEmpty()){
            modelo.put("error", "El email no puede estar vacio");
            return modelo;
        }
        if (!cliente.getEmail().endsWith(".com")){
            modelo.put("error", "El email debe tener un formato valido");
            return modelo;
        }
        if (cliente.getPassword().isEmpty()){
            modelo.put("error", "La contraseña no puede estar vacia");
            return modelo;
        }
        if (cliente.getAbonado() == null){
            modelo.put("error", "El campo abonado no puede ser nulo");
            return modelo;
        }

        modelo.put("error", "");


        return modelo;
    }


}
