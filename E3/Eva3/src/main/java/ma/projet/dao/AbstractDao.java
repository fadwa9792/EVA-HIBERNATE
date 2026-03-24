
package ma.projet.dao;

import ma.projet.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public abstract class AbstractDao<T> implements IDao<T> {
    private Class<T> clazz;

    public AbstractDao(Class<T> clazz) { this.clazz = clazz; }

    @Override
    public void create(T t) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.persist(t);
        tx.commit();
        session.close();
    }

    @Override
    public void update(T t) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.merge(t);
        tx.commit();
        session.close();

    }

    @Override
    public void delete(T t) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.remove(t);
        tx.commit();
        session.close();
    }

    @Override
    public T findById(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        T t = session.get(clazz, id);
        session.close();
        return t;
    }

    @Override
    public List<T> findAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<T> list = session.createQuery("from " + clazz.getSimpleName(), clazz).list();
        session.close();
        return list;
    }
}