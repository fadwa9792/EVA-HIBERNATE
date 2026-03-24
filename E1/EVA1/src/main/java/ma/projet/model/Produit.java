package ma.projet.model;

import javax.persistence.*;
import java.util.List;
import javax.persistence.Entity;
import ma.projet.model.Categorie;
import ma.projet.model.LigneCommandeProduit;


@Entity
@NamedQuery(
        name = "Produit.prixSuperieur",
        query = "select p from Produit p where p.prix > 100"
)
@Table(name = "produit")

@NamedQuery(
        name = "Produit.prixSuperieur100",
        query = "from Produit p where p.prix > 100"
)
public class Produit {
    @Id
    @GeneratedValue
    private int id;
    private String reference;
    private float prix;

    @ManyToOne
    @JoinColumn
    private Categorie categorie;

    @OneToMany(mappedBy = "produit")
    private List<LigneCommandeProduit> ligneCommandeProduis;

    public Produit() {
    }

    public Produit( String reference, float prix, Categorie categorie) {

        this.reference = reference;
        this.prix = prix;
        this.categorie = categorie;


    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public List<LigneCommandeProduit> getligneCommandeProduis() {
        return ligneCommandeProduis;
    }

    public void setligneCommandeProduis(List<LigneCommandeProduit> ligneCommandeProduis) {
        this.ligneCommandeProduis = ligneCommandeProduis;
    }
}