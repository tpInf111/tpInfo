package com.gestionnaireLivraisons;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 * La classe qui modélise un livreur.
 */
public abstract class Livreur {
    // Les attrbuts d'un livreur
    // TODO : À compléter/modifier
    int id;
    String nom;
    ListeLivraisons livraisonsEnCours;
    ListeLivraisons livraisonsEffectuees;

    /**
     * Construit un nouveau livreur.
     *
     * @param id  L'id du nouveau livreur.
     * @param nom Le nom du nouveau livreur.
     */
    // TODO : À compléter/modifier
    public Livreur(int id,String nom){
        this.id=id;
        this.nom=nom;
        this.livraisonsEnCours = new ListeLivraisons();
        this.livraisonsEffectuees = new ListeLivraisons();
    }

    /**
     * L'accesseur pour l'id du livreur.
     *
     * @return L'id de ce livreur.
     */
    public int getId() {
        // TODO : À compléter/modifier
        return this.id;
    }

    /**
     * Getter pour le livreur.
     *
     * @return Le nom de ce livreur.
     */
    public String getNom() {
        // TODO : À compléter/modifier
        return this.nom;
    }

    /**
     * Vérifie si un livreur a des livraisons en cours.
     *
     * @return true si oui, false sinon.
     */
    public boolean aDesLivraisonsEnCours() {
        // TODO : À compléter/modifier
        return this.livraisonsEnCours.taille()>0;
    }

    /**
     * trouve le nombre de livraisons en cours
     *
     * @return le nombre de livraisons en cours
     */
    public int nbLivraisonsEnCours(){
        return this.livraisonsEnCours.taille();
    }

    /**
     * Ajoute une livraison aux livraisons en cours.
     *
     * @param livraison La livraison à ajouter.
     */
    public void ajouterLivraisonEnCours(Livraison livraison) {
        // TODO : À compléter/modifier
        this.livraisonsEnCours.ajouter(livraison);
    }

    /**
     * Ajoute une livraison aux livraisons effectuées.
     *
     * @param livraison La livraison à ajouter.
     */
    public void ajouterLivraisonEffectuee(Livraison livraison) {
        // TODO : À compléter/modifier
        this.livraisonsEffectuees.ajouter(livraison);
    }

    /**
     * Supprimer une livraison des livraisons en cours.
     *
     * @param idLivraison L'i de la livraison à supprimer.
     * @return La livraison supprimée ou null si non trouvée.
     */
    public Livraison supprimerLivraisonEnCours(int idLivraison) {
        // TODO : À compléter/modifier
        return this.livraisonsEnCours.supprimer(idLivraison);
    }

    /**
     * Supprime et retourne toutes les livraisons en cours pour ce livreur.
     *
     * @return La liste des livraisons en cours avant suppressions.
     */
    public IListeLivraisons supprimerToutesLesLivraisons() {
        // TODO : À compléter/modifier
        //nouvelle liste (de type ListeLivraisons)
        ListeLivraisons listeARetourner = new ListeLivraisons();

        //parcour chaque livraison de la liste pour le rajouter a la liste a retourner
        Iterator<Livraison> iterateur = this.livraisonsEnCours.iterator();
        while (iterateur.hasNext()) {
            Livraison liv = iterateur.next();
            // on l'ajoute a la nouvelle liste
            listeARetourner.ajouter(liv);
        }

        //vider la liste du livreur
        this.livraisonsEnCours.vider();

        //retourne la nouvell liste
        return listeARetourner;
    }

    /**
     * Retourne une livraison d'après son id.
     *
     * @param idLivraison L'id de la livraison à trouver.
     * @return La livraison si trouvée, null sinon.
     */
    public Livraison rechercherLivraisonEnCours(int idLivraison) {
        // TODO : À compléter/modifier
        return this.livraisonsEnCours.rechercher(idLivraison);
    }

    /**
     * Calcule et retourne les revenus générés par ce livreur.
     *
     * @return Le revenu calculé.
     */
    public abstract double calculerRevenu();

    /**
     * Retourne la capacité de livraison pour ce livreur.
     *
     * @return La capacité du livreur.
     */
    public abstract int capaciteLivraison();

    /**
     * Retourne un itérateur sur les livraisons en cours de ce livreur.
     *
     * @return L'itérateur.
     */
    public Iterator<Livraison> donneIterateurLivraisonsEnCours() {
        // TODO : À compléter/modifier
        return this.livraisonsEnCours.iterator();
    }

    /**
     * Retourne le nombre de livraisons que ce livreur a effectué
     *
     * @return Le nombre de livraisons.
     */
    public int nbLivraisonsEffectuees() {
        // TODO : À compléter/modifier
        return this.livraisonsEffectuees.taille();
    }
}
