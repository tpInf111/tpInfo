package com.gestionnaireLivraisons;


public class ListeChaineeLivreurs implements IListeChaineeLivreurs {
    private Noeud tete;
    private Noeud dernier;
    private int nbreElements;

    /**
     * Constructeur
     */
    public ListeChaineeLivreurs(){
        this.tete = null;
        this.dernier = null;
        this.nbreElements = 0;
    }

    /**
     * Ajoute un objet Livreur à la fin de la liste
     *
     * @param unLivreur Le livreur à ajouter.
     */
    @Override
    public void ajouter(Livreur unLivreur) throws ListeChaineeException {
        //si notre livreur est null
        if(unLivreur==null ){
            throw new ListeChaineeException("Erreur : impossible d'ajouter un livreur null a la liste. ");
        }
        //si le nouveau element est le premier de la liste
        Noeud nouveau = new Noeud(unLivreur);
        if(tete==null){
            tete=nouveau;
            dernier=nouveau;
        }
        //s'il n'est  pas le nouveau de la liste il faut le rajouter au dernier
        else{
            dernier.suivant=nouveau;
            dernier = nouveau;
        }
        nbreElements++;
    }

    /**
     * Supprime un livreur de la liste
     *
     * @param idLivreur : identifiant du livreur à supprimer
     * @return true si suppression, false sinon, car le livreur n'existe pas dans la liste
     */
    @Override
    public boolean supprimer(int idLivreur) {
        //voir si la liste est vide
        if(tete==null){
            return false;
        }
        //si la tete est le livreur a supprimer
        if(tete.livreur.getId()==idLivreur){
            if(tete==dernier){
                dernier=null;
            }
            tete=tete.suivant;
            nbreElements--;
            return true;
        }
        Noeud courant = tete;
        while (courant.suivant != null) {
            if (courant.suivant.livreur.getId() == idLivreur) {
                if (courant.suivant == dernier) {
                    dernier = courant;
                }
                courant.suivant = courant.suivant.suivant;
                nbreElements--;
                return true;
            }
            courant = courant.suivant;
        }

        // si on a parcouru toute la liste sans trouver le livreur
        return false;
    }


    /**
     * Recherche un livreur par son id et le retourne.
     *
     * @param idLivreur identifiant du livreur à retrouver.
     * @return Le livreur ou null si non trouvé.
     */
    @Override
    public Livreur rechercher(int idLivreur) {
        Noeud courant = tete;
        while (courant!=null){
            if (courant.livreur.getId()==idLivreur) {
                return courant.livreur;
            }
            courant=courant.suivant;
        }
        return null;
    }
    /*
      Affiche les élement dans la chaine
     */
    public void affiche(){
        Noeud courant = tete;
        while (courant!=null){
            System.out.println(courant.livreur);
            courant=courant.suivant;
        }
    }
    /**
     * Retourne le nombre d'éléments se trouvant dans la liste chaînée.
     *
     * @return Le nombre d'éléments.
     */
    @Override
    public int taille() {
        return this.nbreElements;
    }

    /**
     * Crée et retourne un tableau de livreurs pour cette liste chaînée de livreurs.
     *
     * @return Le tableau de livreurs.
     */
    public Livreur[] toArray() {
        Livreur[] tableau = new Livreur[nbreElements];
        Noeud courant = tete;
        int index = 0;

        while (courant!=null){
            tableau[index]=courant.livreur;
            courant= courant.suivant;
            index++;
        }
        return tableau;
    }

    /**
     * Implémente un neoud de la liste chainée
     *
     */
    private static class Noeud {
        private final Livreur livreur;
        private Noeud suivant;

        Noeud(Livreur livreur) {
            this.livreur = livreur;
            this.suivant = null;
        }
    }
}
