package com.gestionnaireLivraisons;

import java.util.ArrayList;

/**
 * La classe qui modélise une livraison.
 */
public class Livraison
        // TODO : À compléter/modifier
{

    // Les données membres statiques
    private static int numSequentiel=0;
    // TODO : À compléter/modifier

    // Les attributs d'instance
    private int id;
    private Priorite priorite;
    private int tentative ;
    private int lot;
    private Statut statut;
    // TODO : À compléter/modifier

    /**
     * Constructeur d'une livraison.
     *
     * @param priorite La priorité de la nouvelle livraison.
     * @param lot      Le lot auquel cette livraison appartient.
     */
    // TODO : À compléter/modifier
    public Livraison(Priorite priorite, int lot){
        this.priorite = priorite;
        this.lot = lot;
        this.id = prochainID();
        this.tentative =0;
        this.statut = Statut.EN_ATTENTE;
    }

    /**
     * Produit un nouvel ID pour la Livraison
     * @return prochainNumSequentiel : donne la valeur du nouveu numéro séquantiel
     */
    private static int prochainID() {
        // TODO : À compléter/modifier
        numSequentiel ++;
        return numSequentiel;
    }

    /**
     * Retourne l'identifiant de cette livraison.
     *
     * @return L'id de cette livraison.
     */
    public int getId() {
        // TODO : À compléter/modifier
        return 0;
    }

    /**
     * Retourne la priorité de cette livraison.
     *
     * @return La priorite de cette livraison.
     */
    // TODO : À compléter/modifier

    /**
     * Retourne la tentative pour cette livraison.
     *
     * @return La tentative de cette livraison.
     */
    public int getTentative() {
        // TODO : À compléter/modifier
        return 0;
    }

    /**
     * Retourne le lot auquel appartient cette livraison.
     *
     * @return Le lot de cette livraison.
     */
    public int getLot() {
        // TODO : À compléter/modifier
        return 0;
    }

    /**
     * Mutateur pour le statut de la livraison.
     *
     */
    public void setStatut(Statut statut) {
        // TODO : À compléter/modifier
    }

    /**
     * Ajoute UN au numéro de tentative pour cette livraison.
     *
     * @return False si on a atteint le nombre maximal de tentatives pour cette livraison. True sinon.
     */
    public boolean nouvelleTentative() {
        // TODO : À compléter/modifier
        return false;

    }

    /**
     * Construit et retourne une chaîne de caractères équivalente à cette livraison.
     *
     * @return String La chaîne qui représente cette livraison.
     */
    @Override
    public String toString() {
        // TODO : À compléter/modifier
        return null;

    }

    /**
     * Compare cette livraison avec une autre livraison.
     *
     * @param autreLivraison La seconde livraison à comparer avec cette livraison.
     * @return Le résultat de la comparaison au sens de l'interface Comparable<T>.
     */
    // TODO : À compléter/modifier
}
