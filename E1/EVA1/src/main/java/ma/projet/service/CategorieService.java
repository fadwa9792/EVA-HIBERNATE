package ma.projet.service;

import ma.projet.model.Categorie;
import ma.projet.dao.AbstractFacade;

public class CategorieService extends AbstractFacade<Categorie> {

    public CategorieService() {
        super(Categorie.class);
    }

}