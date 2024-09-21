package com.tallerwebi.dominio;


import com.tallerwebi.dominio.excepcion.ClienteExistente;
import com.tallerwebi.dominio.excepcion.PasswordLongitudIncorrecta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ServicioClienteImpl implements ServicioCrearCliente {

    RepositorioCliente repositorioCliente;

    @Autowired
    public ServicioClienteImpl(RepositorioCliente repositorioCliente){
        this.repositorioCliente = repositorioCliente;
    }

    @Override
    public Cliente crearCliente(Cliente cliente) throws PasswordLongitudIncorrecta, ClienteExistente {

        if(cliente.getPassword().length() < 5){
            throw new PasswordLongitudIncorrecta();
        }


        if (repositorioCliente.buscarEmail(cliente.getEmail()) != null){
            throw new ClienteExistente("El mail utilizado ya fue usado para crear un cliente");
        }

        if (repositorioCliente.buscarDni(cliente.getDni()) != null){
            throw new ClienteExistente("El dni utilizado ya fue usado para crear un cliente");
        }

        cliente.setAdmin(false);
        repositorioCliente.guardarCliente(cliente);
        return cliente;
    }

}
