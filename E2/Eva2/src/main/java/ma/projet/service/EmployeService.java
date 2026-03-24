package ma.projet.service;

import ma.projet.model.*;
import org.hibernate.Session;
import ma.projet.util.HibernateUtil;

import java.util.List;

public class EmployeService extends ma.projet.service.AbstractFacade<Employe> {

    public EmployeService() {
        super(Employe.class);
    }

    // Liste des tâches réalisées par un employé
    public List<EmployeTache> tachesEmploye(int id){

        Session session = HibernateUtil.getSessionFactory().openSession();

        List<EmployeTache> list =
                session.createQuery("from EmployeTache et where et.employe.id = :id")
                        .setParameter("id",id)
                        .list();

        session.close();

        return list;
    }

    // Liste des projets gérés par un employé
    public List<Projet> projetsEmploye(int id){

        Session session = HibernateUtil.getSessionFactory().openSession();

        List<Projet> list =
                session.createQuery("from Projet p where p.chefProjet.id = :id")
                        .setParameter("id",id)
                        .list();

        session.close();

        return list;
    }

}