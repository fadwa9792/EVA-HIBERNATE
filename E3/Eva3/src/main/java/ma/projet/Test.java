package ma.projet;

import ma.projet.model.*;
import ma.projet.service.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class Test {
    public static void main(String[] args) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        HommeService hs = new HommeService();
        FemmeService fs = new FemmeService();
        MariageService ms = new MariageService();

        // Création des hommes
        Homme h1 = new Homme(); h1.setNom("SAID"); h1.setPrenom("Safi");
        Homme h2 = new Homme(); h2.setNom("ALI"); h2.setPrenom("Ahmed");
        Homme h3 = new Homme(); h3.setNom("KARIM"); h3.setPrenom("Omar");
        Homme h4 = new Homme(); h4.setNom("MOHAMED"); h4.setPrenom("Reda");
        Homme h5 = new Homme(); h5.setNom("YASSIN"); h5.setPrenom("Nabil");
        hs.create(h1); hs.create(h2); hs.create(h3); hs.create(h4); hs.create(h5);

        // Création des femmes
        List<Femme> femmes = new ArrayList<>();
        for(int i=1;i<=10;i++){
            Femme f = new Femme();
            f.setNom("Femme"+i); f.setPrenom("Prenom"+i);
            f.setDateNaissance(sdf.parse("01/01/19"+(70+i)));
            fs.create(f);
            femmes.add(f);
        }

        // Création des mariages
        Mariage m1 = new Mariage(); m1.setHomme(h1); m1.setFemme(femmes.get(0)); m1.setDateDebut(sdf.parse("03/09/1990")); m1.setNbrEnfant(4); ms.create(m1);
        Mariage m2 = new Mariage(); m2.setHomme(h1); m2.setFemme(femmes.get(1)); m2.setDateDebut(sdf.parse("03/09/1995")); m2.setNbrEnfant(2); ms.create(m2);
        Mariage m3 = new Mariage(); m3.setHomme(h1); m3.setFemme(femmes.get(2)); m3.setDateDebut(sdf.parse("04/11/2000")); m3.setNbrEnfant(3); ms.create(m3);
        Mariage m4 = new Mariage(); m4.setHomme(h1); m4.setFemme(femmes.get(3)); m4.setDateDebut(sdf.parse("03/09/1989")); m4.setDateFin(sdf.parse("03/09/1990")); m4.setNbrEnfant(0); ms.create(m4);

        // Exécution des méthodes
        System.out.println("Liste des femmes : " + fs.findAll());
        System.out.println("Femme la plus âgée : " + fs.findAll().stream().min(Comparator.comparing(Femme::getDateNaissance)).get());
        System.out.println("Epouses de h1 entre 1990 et 2000 : " + hs.epousesEntreDates(h1.getId(), sdf.parse("01/01/1990"), sdf.parse("31/12/2000")));
        System.out.println("Nombre d'enfants de Femme1 entre 1990 et 2000 : " + fs.nombreEnfantsEntreDates(femmes.get(0).getId(), sdf.parse("01/01/1990"), sdf.parse("31/12/2000")));
        System.out.println("Femmes mariées deux fois ou plus : " + fs.femmesMarieesDeuxFoisOuPlus());
        System.out.println("Hommes mariés à 4 femmes entre 1980 et 2010 : " + hs.hommesMariesAQuatreFemmes(sdf.parse("01/01/1980"), sdf.parse("31/12/2010")));
        System.out.println("Mariages détaillés de h1 : " + hs.mariagesDetails(h1.getId()));
    }
}