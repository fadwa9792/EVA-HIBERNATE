package ma.projet.service;

import ma.projet.model.*;
import org.hibernate.Session;
import ma.projet.util.HibernateUtil;

import java.util.List;

public class ProjetService extends AbstractFacade<Projet>{

    public ProjetService() {
        super(Projet.class);
    }

    // Liste des tâches d’un projet
    public List<Tache> tachesProjet(int id){

        Session session = HibernateUtil.getSessionFactory().openSession();

        List<Tache> list =
                session.createQuery("from Tache t where t.projet.id = :id")
                        .setParameter("id",id)
                        .list();

        session.close();

        return list;
    }

    // tâches réalisées avec dates réelles
    public List<EmployeTache> tachesRealisees(int id){

        Session session = HibernateUtil.getSessionFactory().openSession();

        List<EmployeTache> list =
                session.createQuery(
                                "select et from EmployeTache et where et.tache.projet.id = :id")
                        .setParameter("id",id)
                        .list();

        session.close();

        return list;
    }

}