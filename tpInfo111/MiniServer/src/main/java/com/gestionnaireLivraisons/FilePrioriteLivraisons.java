package com.gestionnaireLivraisons;

import java.util.PriorityQueue;

// import static com.gestionnaireLivraisons.Livraison;

/**
 * La classe générique des files de priorité.
 *
 */
public class FilePrioriteLivraisons {
    // Les livraisons stockées dans une file de priorité
    // TODO : À compléter/modifier
    private PriorityQueue<Livraison> fileLivraisonPrio;
    /**
     * Construit une file de priorité.
     */
    // TODO : À compléter/modifier
    public FilePrioriteLivraisons(){
        this.fileLivraisonPrio = new PriorityQueue<>();
    }
    /**
     * Retire et retourne l'élément le plus prioritaire de la file.
     *
     * @return L'élément le plus prioritaire.
     */
    public Livraison retirer() {
        // TODO : À compléter/modifier
        return this.fileLivraisonPrio.poll();
    }

    /**
     * Ajoute un élément à la file.
     *
     * @param element L'élément à ajouter à la file.
     */
    public void ajouter(Livraison element) {
        // TODO : À compléter/modifier
        this.fileLivraisonPrio.add(element);
    }

    /**
     * Ajoute un ensemble d'éléments à la file.
     *
     * @param elements L'ensemble des éléments à ajouter à la file.
     */
    public void ajouterTout(Iterable<Livraison> elements) {
        // TODO : À compléter/modifier
        for(Livraison livraison :elements){
            this.fileLivraisonPrio.add(livraison);
        }
    }

    /**
     * Indique si la file est vide.
     *
     * @return True si la file est vide. False sinon.
     */
    public boolean estVide() {
        // TODO : À compléter/modifier
        return this.fileLivraisonPrio.size()==0;
    }

    /**
     * Retourne le nombre d'éléments dans cette file de priorité.
     *
     * @return Le nombre d'éléments de la file.
     */
    public int taille() {
        // TODO : À compléter/modifier
        return this.fileLivraisonPrio.size();
    }

    /**
     * Affiche les éléments contenus dans la file.
     *
     */
    public void afficher() {
        // DODO : À compléter/modifier
        for(Livraison livraison : this.fileLivraisonPrio){
            System.out.print(livraison);
        }
    }
}
