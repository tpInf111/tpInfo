package com.gestionnaireLivraisons;

import java.util.ArrayList;

/**
 * La classe qui modélise une livraison.
 */
public class Livraison implements Comparable<Livraison>
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

    // la constante valant 3
    final int MAX_TENTATIVES = 3;

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
        return this.id;
    }

    /**
     * Retourne la priorité de cette livraison.
     *
     * @return La priorite de cette livraison.
     */
    public Priorite getPriorite(){
        // TODO : À compléter/modifier
        return this.priorite;
    }

    /**
     * Retourne la tentative pour cette livraison.
     *
     * @return La tentative de cette livraison.
     */
    public int getTentative() {
        // TODO : À compléter/modifier
        return this.tentative;
    }

    /**
     * Retourne le lot auquel appartient cette livraison.
     *
     * @return Le lot de cette livraison.
     */
    public int getLot() {
        // TODO : À compléter/modifier
        return this.lot;
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
        if(this.tentative<MAX_TENTATIVES){
            this.tentative++;
            return true;
        }else{
            return false;
        }
    }

    /**
     * Construit et retourne une chaîne de caractères équivalente à cette livraison.
     *
     * @return String La chaîne qui représente cette livraison.
     */
    @Override
    public String toString() {
        // TODO : À compléter/modifier
        return "livraison : "+ id +"\n"+
                " [ priorite :" + priorite +
                " ,tentative :" + tentative +"/"+ MAX_TENTATIVES +
                " , Lot :" + lot +
                " , Statut :"+ statut+" ]";
    }

    /**
     * Compare cette livraison avec une autre livraison.
     * return -1 si objet courant est prioritaire a autreLivraion
     * return 1 si autreLivraion est prioritaire a objet courant
     * return 0 s'ils ont les deux la meme prioritaire 
     *
     * @param autreLivraison La seconde livraison à comparer avec cette livraison.
     * @return Le résultat de la comparaison au sens de l'interface Comparable<T>.
     */
    @Override
    public int compareTo(Livraison autreLivraison){
        // TODO : À compléter/modifier
        // 1- le lot plus petit prioritaire
       if(this.lot< autreLivraison.getLot()){
           return -1;
       } else if (autreLivraison.getLot()<this.lot ) {
           return 1;
       }
       // si meme lot prendre celuis avec urgentce (mais ne doit pas etre les deux)
       boolean thisUr = this.priorite == Priorite.URGENTE;
       boolean autreUr = autreLivraison.getPriorite()== Priorite.URGENTE;
       if(thisUr && !autreUr) return -1;
       if(autreUr && !thisUr) return 1;

       //si meme lot meme prioritie prendre celuis avec plus de tentative
        if (autreLivraison.getTentative()<this.tentative) {
           return -1;
       } else if (this.tentative< autreLivraison.getTentative()) {
           return 1;
       }
        // si tu est égale returne s'implememt 1
        return 0;

    }
}
