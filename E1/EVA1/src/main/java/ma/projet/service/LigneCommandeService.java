package ma.projet.service;

import ma.projet.dao.AbstractFacade;
import ma.projet.model.LigneCommandeProduit;

public class LigneCommandeService extends AbstractFacade<LigneCommandeProduit> {

    public LigneCommandeService() {
        super(LigneCommandeProduit.class);
    }

}