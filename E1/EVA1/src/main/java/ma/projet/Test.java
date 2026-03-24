package ma.projet;

import ma.projet.model.*;
import ma.projet.service.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Test {

    public static void main(String[] args) throws Exception {

        // Services
        CategorieService cs = new CategorieService();
        ProduitService ps = new ProduitService();
        CommandeService cms = new CommandeService();
        LigneCommandeService ls = new LigneCommandeService();

        System.out.println("===== CREATION DES CATEGORIES =====");

        Categorie cat1 = new Categorie("PC", "Ordinateurs");
        Categorie cat2 = new Categorie("IMP", "Imprimantes");

        cs.create(cat1);
        cs.create(cat2);

        System.out.println("===== CREATION DES PRODUITS =====");

        Produit p1 = new Produit("ES12", 120, cat1);
        Produit p2 = new Produit("ZR85", 100, cat1);
        Produit p3 = new Produit("EE85", 200, cat2);

        ps.create(p1);
        ps.create(p2);
        ps.create(p3);

        System.out.println("===== CREATION DES COMMANDES =====");

        Date d1 = new SimpleDateFormat("yyyy-MM-dd").parse("2012-01-01");
        Date d2 = new SimpleDateFormat("yyyy-MM-dd").parse("2014-01-01");

        Commande cmd1 = new Commande(d1);
        Commande cmd2 = new Commande(d2);

        cms.create(cmd1);
        cms.create(cmd2);

        System.out.println("===== CREATION DES LIGNES DE COMMANDE =====");

        // Ligne 1
        CommandeProduitPK pk1 = new CommandeProduitPK(cmd1.getId(), p1.getId());
        LigneCommandeProduit l1 = new LigneCommandeProduit();
        l1.setPk(pk1);
        l1.setCommande(cmd1);
        l1.setProduit(p1);
        l1.setQuantite(7);
        ls.create(l1);

        // Ligne 2
        CommandeProduitPK pk2 = new CommandeProduitPK(cmd1.getId(), p2.getId());
        LigneCommandeProduit l2 = new LigneCommandeProduit();
        l2.setPk(pk2);
        l2.setCommande(cmd1);
        l2.setProduit(p2);
        l2.setQuantite(14);
        ls.create(l2);

        // Ligne 3
        CommandeProduitPK pk3 = new CommandeProduitPK(cmd1.getId(), p3.getId());
        LigneCommandeProduit l3 = new LigneCommandeProduit();
        l3.setPk(pk3);
        l3.setCommande(cmd1);
        l3.setProduit(p3);
        l3.setQuantite(5);
        ls.create(l3);

        System.out.println("\n===== PRODUITS PAR CATEGORIE =====");

        List<Produit> produitsCategorie = ps.findByCategorie(cat1);
        for (Produit p : produitsCategorie) {
            System.out.println(p.getReference() + "  " + p.getPrix());
        }

        System.out.println("\n===== PRODUITS D'UNE COMMANDE =====");

        ps.produitsParCommande(cmd1.getId());


        System.out.println("\n===== PRODUITS ENTRE DEUX DATES =====");

        List<Produit> produitsDates = ps.produitsEntreDates(d1, d2);
        for (Produit p : produitsDates) {
            System.out.println(p.getReference() + " " + p.getPrix());
        }


        System.out.println("\n===== PRODUITS PRIX > 100 =====");

        List<Produit> produitsCher = ps.produitsPrixSup100();
        for (Produit p : produitsCher) {
            System.out.println(p.getReference() + " " + p.getPrix());
        }


    }
}