package ma.projet.dao;

import ma.projet.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public abstract class AbstractFacade<T> {

    private Class<T> entityClass;

    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public void create(T entity) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        try {
            session.save(entity);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        }

        session.close();
    }

    public void edit(T entity) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        try {
            session.update(entity);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        }

        session.close();
    }

    public void remove(T entity) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        try {
            session.delete(entity);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        }

        session.close();
    }

    public T find(int id) {

        Session session = HibernateUtil.getSessionFactory().openSession();

        T obj = session.get(entityClass, id);

        session.close();

        return obj;
    }

    public List<T> findAll() {

        Session session = HibernateUtil.getSessionFactory().openSession();

        List<T> list = session.createQuery(
                "from " + entityClass.getSimpleName(), entityClass
        ).getResultList();

        session.close();

        return list;
    }
}