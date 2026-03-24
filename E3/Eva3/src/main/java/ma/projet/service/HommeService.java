
package ma.projet.service;

import ma.projet.model.Homme;
import ma.projet.model.Mariage;
import ma.projet.dao.AbstractDao;
import ma.projet.util.HibernateUtil;
import org.hibernate.Session;

import java.util.Date;
import java.util.List;

public class HommeService extends AbstractDao<Homme> {

    public HommeService() { super(Homme.class); }

    public List<Mariage> epousesEntreDates(int hommeId, Date start, Date end) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Mariage> list = session.createQuery(
                        "from Mariage m where m.homme.id=:hid and m.dateDebut between :start and :end", Mariage.class)
                .setParameter("hid", hommeId)
                .setParameter("start", start)
                .setParameter("end", end)
                .list();
        session.close();
        return list;
    }

    public List<Homme> hommesMariesAQuatreFemmes(Date start, Date end) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Homme> list = session.createQuery(
                        "select m.homme from Mariage m where m.dateDebut between :start and :end " +
                                "group by m.homme having count(m.femme)=4", Homme.class)
                .setParameter("start", start)
                .setParameter("end", end)
                .list();
        session.close();
        return list;
    }

    public List<Mariage> mariagesDetails(int hommeId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Mariage> list = session.createQuery(
                        "from Mariage m where m.homme.id=:hid", Mariage.class)
                .setParameter("hid", hommeId)
                .list();
        session.close();
        return list;
    }
}