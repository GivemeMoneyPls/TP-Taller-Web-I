package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.Recorrido;
import com.tallerwebi.dominio.RepositorioRecorrido;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Repository("repositorioRecorrido")
public class RepositorioRecorridoImpl implements RepositorioRecorrido {


    private SessionFactory sessionFactory;

    @Autowired
    public RepositorioRecorridoImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }


    @Override
    public void guardar(Recorrido recorrido) {
           sessionFactory.getCurrentSession().save(recorrido);
    }

    @Override
    public List<Recorrido> getRecorridos() {
        return sessionFactory.getCurrentSession()
                             .createQuery("FROM Recorrido", Recorrido.class)
                             .getResultList();
    }


}
