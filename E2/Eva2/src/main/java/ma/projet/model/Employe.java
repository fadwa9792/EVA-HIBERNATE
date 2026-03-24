package ma.projet.model;

import javax.persistence.*;
import java.util.*;
import javax.persistence.Entity;
import javax.persistence.*;


@Entity
public class Employe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nom;
    private String prenom;
    private String telephone;

    @OneToMany(mappedBy = "chefProjet")
    private List<Projet> projets;

    @OneToMany(mappedBy = "employe")
    private List<EmployeTache> employeTaches;

    public Employe() {
    }

    public Employe(String nom, String prenom, String telephone) {
        this.nom = nom;
        this.prenom = prenom;
        this.telephone = telephone;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

}