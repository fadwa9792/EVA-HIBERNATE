package ma.projet.service;

import ma.projet.model.Categorie;
import ma.projet.model.Commande;
import ma.projet.model.LigneCommandeProduit;
import ma.projet.model.Produit;
import ma.projet.dao.AbstractFacade;
import ma.projet.util.HibernateUtil;
import org.hibernate.Session;

import java.util.Date;
import java.util.List;

public class ProduitService extends AbstractFacade<Produit> {

    public ProduitService() {
        super(Produit.class);
    }










    //  Produits par catégorie
    public List<Produit> findByCategorie(Categorie c){

        Session session = HibernateUtil.getSessionFactory().openSession();

        List<Produit> produits = session.createQuery(
                        "from Produit p where p.categorie.id = :id"
                )
                .setParameter("id", c.getId())
                .list();

        session.close();

        return produits;
    }




    // Produits d'une commande donnée
    public void produitsParCommande(int idCommande){

        Session session = HibernateUtil.getSessionFactory().openSession();

        List<LigneCommandeProduit> lignes = session.createQuery(
                        "from LigneCommandeProduit l where l.commande.id = :id"
                )
                .setParameter("id", idCommande)
                .list();

        Commande c = session.get(Commande.class,idCommande);

        System.out.println("Commande : "+c.getId()+" Date : "+c.getDate());
        System.out.println("Reference   Prix   Quantite");

        for(LigneCommandeProduit l : lignes){

            Produit p = l.getProduit();

            System.out.println(
                    p.getReference()+"   "
                            +p.getPrix()+"   "
                            +l.getQuantite()
            );
        }

        session.close();
    }


    //  Produits prix > 100 (NamedQuery)
    public List<Produit> produitsPrixSup100(){

        Session session = HibernateUtil.getSessionFactory().openSession();

        List<Produit> produits = session
                .createNamedQuery("Produit.prixSuperieur", Produit.class)
                .getResultList();

        session.close();

        return produits;
    }


    // Produits commandés entre deux dates
    public List<Produit> produitsEntreDates(Date d1, Date d2){

        Session session = HibernateUtil.getSessionFactory().openSession();

        List<Produit> produits = session.createQuery(
                        "select l.produit from LigneCommandeProduit l "
                                + "where l.commande.date between :d1 and :d2"
                )
                .setParameter("d1", d1)
                .setParameter("d2", d2)
                .list();

        session.close();

        return produits;
    }
}