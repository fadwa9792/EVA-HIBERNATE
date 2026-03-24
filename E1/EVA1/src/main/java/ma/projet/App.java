package ma.projet;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import static ma.projet.util.HibernateUtil.sessionFactory;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {

        SessionFactory factory = new Configuration().configure().buildSessionFactory();

    }
}
