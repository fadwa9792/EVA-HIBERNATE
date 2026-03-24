package ma.projet.service;

import ma.projet.model.Femme;
import ma.projet.dao.AbstractDao;
import ma.projet.util.HibernateUtil;
import org.hibernate.Session;

import java.util.Date;
import java.util.List;

public class FemmeService extends AbstractDao<Femme> {

    public FemmeService() { super(Femme.class); }

    public long nombreEnfantsEntreDates(int femmeId, Date start, Date end) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Long nbr = session.createQuery(
                        "select sum(m.nbrEnfant) from Mariage m where m.femme.id=:fid and m.dateDebut between :start and :end", Long.class)
                .setParameter("fid", femmeId)
                .setParameter("start", start)
                .setParameter("end", end)
                .uniqueResult();
        session.close();
        return nbr != null ? nbr : 0;
    }

    public List<Femme> femmesMarieesDeuxFoisOuPlus() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Femme> list = session.createQuery(
                        "select m.femme from Mariage m group by m.femme having count(m.homme)>=2", Femme.class)
                .list();
        session.close();
        return list;
    }
}