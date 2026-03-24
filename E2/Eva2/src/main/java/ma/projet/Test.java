package ma.projet;

import ma.projet.model.*;
import ma.projet.service.*;

import java.text.SimpleDateFormat;

public class Test {


    public static void main(String[] args) throws Exception {

        EmployeService es = new EmployeService();
        ProjetService ps = new ProjetService();
        TacheService ts = new TacheService();
        EmployeTacheService ets = new EmployeTacheService();

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        // ===============================
        // Création des employés
        // ===============================

        Employe e1 = new Employe("Ali","Karimi","0600");
        Employe e2 = new Employe("Sara","Amine","0601");

        es.create(e1);
        es.create(e2);

        // ===============================
        // Création du projet
        // ===============================

        Projet p1 = new Projet("Gestion de stock",
                sdf.parse("14/01/2013"),
                sdf.parse("30/06/2013"),
                e1);

        ps.create(p1);

        // ===============================
        // Création des tâches
        // ===============================

        Tache t1 = new Tache("Analyse",
                sdf.parse("01/02/2013"),
                sdf.parse("20/02/2013"),
                1200,p1);

        Tache t2 = new Tache("Conception",
                sdf.parse("01/03/2013"),
                sdf.parse("15/03/2013"),
                900,p1);

        ts.create(t1);
        ts.create(t2);

        // ===============================
        // Affectation des tâches aux employés
        // ===============================

        EmployeTache et1 =
                new EmployeTache(e1,t1,
                        sdf.parse("10/02/2013"),
                        sdf.parse("20/02/2013"));

        EmployeTache et2 =
                new EmployeTache(e2,t2,
                        sdf.parse("05/03/2013"),
                        sdf.parse("15/03/2013"));

        ets.create(et1);
        ets.create(et2);



        System.out.println("\n===== TACHES D'UN EMPLOYE =====");

        es.tachesEmploye(e1.getId()).forEach(et -> {
            System.out.println(
                    et.getTache().getNom() +
                            "  " + et.getDateDebutReelle() +
                            "  " + et.getDateFinReelle()
            );
        });


        System.out.println("\n===== PROJETS D'UN EMPLOYE =====");

        es.projetsEmploye(e1.getId()).forEach(p -> {
            System.out.println(p.getNom());
        });



        System.out.println("\n===== TACHES D'UN PROJET =====");

        ps.tachesProjet(p1.getId()).forEach(t -> {
            System.out.println(
                    t.getNom() + "  " +
                            t.getDateDebut() + "  " +
                            t.getDateFin()
            );
        });


        System.out.println("\n===== TACHES REALISEES D'UN PROJET =====");

        ps.tachesRealisees(p1.getId()).forEach(et -> {

            System.out.println(
                    et.getTache().getNom()+"  "+
                            et.getDateDebutReelle()+"  "+
                            et.getDateFinReelle()
            );

        });


        System.out.println("\n===== TACHES > 1000 DH =====");

        ts.tachesCheres().forEach(t -> {
            System.out.println(
                    t.getNom() + "  " + t.getPrix()
            );
        });



        System.out.println("\n===== TACHES ENTRE DEUX DATES =====");

        ts.tachesEntreDates(
                sdf.parse("01/02/2013"),
                sdf.parse("30/03/2013")
        ).forEach(et -> {

            System.out.println(
                    et.getTache().getNom() +
                            "  " + et.getDateDebutReelle()
            );

        });

    }


}
