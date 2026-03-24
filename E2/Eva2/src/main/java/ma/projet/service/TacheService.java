package ma.projet.service;

import ma.projet.model.*;
import org.hibernate.Session;
import ma.projet.util.HibernateUtil;

import java.util.Date;
import java.util.List;

public class TacheService extends AbstractFacade<Tache>{

    public TacheService() {
        super(Tache.class);
    }

    // requête nommée
    public List<Tache> tachesCheres(){

        Session session = HibernateUtil.getSessionFactory().openSession();

        List<Tache> list =
                session.createNamedQuery("tachePrix").list();

        session.close();

        return list;
    }

    // tâches entre deux dates
    public List<EmployeTache> tachesEntreDates(Date d1, Date d2){

        Session session = HibernateUtil.getSessionFactory().openSession();

        List<EmployeTache> list =
                session.createQuery(
                                "from EmployeTache et where et.dateDebutReelle between :d1 and :d2")
                        .setParameter("d1",d1)
                        .setParameter("d2",d2)
                        .list();

        session.close();

        return list;
    }

}