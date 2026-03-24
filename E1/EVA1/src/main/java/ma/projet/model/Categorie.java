package ma.projet.model;

import ma.projet.model.Produit;
import javax.persistence.Entity;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Categories")
public class Categorie {
    @Id
    @GeneratedValue
    private int id;
    private String code;
    private String libelle;


    @OneToMany(mappedBy = "categorie", fetch = FetchType.EAGER)
    private List<Produit> produits;

    public Categorie() {
    }

    public Categorie(String code, String libelle) {
        this.code = code;
        this.libelle = libelle;
    }

    public Categorie(String code, String libelle, List<Produit> produits) {
        this.code = code;
        this.libelle = libelle;
        this.produits = produits;
    }

    public String getDode() {
        return code;
    }

    public void setDode(String code) {
        this.code = code;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public List<Produit> getProduits() {
        return produits;
    }

    public void setProduits(List<Produit> produits) {
        this.produits = produits;
    }


}