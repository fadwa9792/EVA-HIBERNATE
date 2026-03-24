package ma.projet.service;

import ma.projet.dao.IDao;
import ma.projet.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public abstract class AbstractFacade<T> implements IDao<T> {

    private Class<T> entityClass;

    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    @Override
    public boolean create(T o) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        session.save(o);

        tx.commit();
        session.close();

        return true;
    }

    @Override
    public boolean update(T o) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        session.update(o);

        tx.commit();
        session.close();

        return true;
    }

    @Override
    public boolean delete(T o) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        session.delete(o);

        tx.commit();
        session.close();

        return true;
    }

    @Override
    public T findById(int id) {

        Session session = HibernateUtil.getSessionFactory().openSession();

        T o = session.get(entityClass, id);

        session.close();

        return o;
    }

    @Override
    public List<T> findAll() {

        Session session = HibernateUtil.getSessionFactory().openSession();

        List<T> list = session.createQuery("from " + entityClass.getSimpleName()).list();

        session.close();

        return list;
    }

}