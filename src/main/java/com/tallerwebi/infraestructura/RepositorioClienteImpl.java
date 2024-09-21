package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.Cliente;
import com.tallerwebi.dominio.RepositorioCliente;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("repositorioCliente")
public class RepositorioClienteImpl implements RepositorioCliente {

    SessionFactory sessionFactory;

    @Autowired
    public RepositorioClienteImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Cliente buscarEmail(String email) {
        Session session = sessionFactory.getCurrentSession();
        return (Cliente) session.createCriteria(Cliente.class)
                .add(Restrictions.eq("email", email))
                .uniqueResult();
    }

    @Override
    public void guardarCliente(Cliente cliente) {

        Session session = sessionFactory.getCurrentSession();
        session.save(cliente);

    }

    @Override
    public Cliente buscarDni(String dni) {

        Session session = sessionFactory.getCurrentSession();
        return (Cliente) session.createCriteria(Cliente.class)
                .add(Restrictions.eq("dni", dni))
                .uniqueResult();
    }
}
