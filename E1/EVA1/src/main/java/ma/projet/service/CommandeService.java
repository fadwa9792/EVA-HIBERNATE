package ma.projet.service;

import ma.projet.model.*;
import ma.projet.dao.AbstractFacade;

public class CommandeService extends AbstractFacade<Commande> {

    public CommandeService() {
        super(Commande.class);
    }

}