package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.Chofer;
import com.tallerwebi.dominio.RepositorioChofer;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.hibernate.Session;

@Repository("repositorioChofer")

public class RepositorioChofeImpl implements RepositorioChofer {

    private SessionFactory sessionFactory;

    @Autowired
    public RepositorioChofeImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Chofer buscarUsuario(Long dni) {

        final Session session = sessionFactory.getCurrentSession();
        return (Chofer) session.createCriteria(Chofer.class)
                .add(Restrictions.eq("dni", dni))
                .uniqueResult();
    }

    @Override
    public void guardar(Chofer chofer) {
          sessionFactory.getCurrentSession().save(chofer);
    }
}
