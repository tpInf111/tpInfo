package com.gestionnaireLivraisons;

import java.util.ArrayList;
import java.util.ListIterator;

/**
 * La classe qui liste des livraisons.
 */
public class ListeLivraisons implements IListeLivraisons, Iterable<Livraison> {
    // Les livraisons stockées dans une ArrayList
    // done : À compléter/modifier
    private ArrayList<Livraison> listeLivraison = new ArrayList<>();

    /**
     * Ajout d'une livraison à la liste de livraisons.
     *
     * @param livraison La livraison à ajouter.
     */
    public void ajouter(Livraison livraison) {
        listeLivraison.add(livraison);
        // DONE : À compléter/modifier
    }

    /**
     * Supprime une livraison par son id.
     *
     * @param idLivraison L'id de la livraison à supprimer.
     * @return La livraison supprimée ou null si non trouvée.
     */
    public Livraison supprimer(int idLivraison) {
        // done : À compléter/modifier DONE
        int index =chercher(idLivraison);
        if (index!=-1){
            return listeLivraison.remove(index);
        }
        return null;
    }

    /**
     * Recherche une livraison par son id et la retourne.
     *
     * @param idLivraison L'id de la livraison à chercher.
     * @return La livraison trouvée ou null si non trouvée.
     */
    public Livraison rechercher(int idLivraison) {
        // done : À compléter/modifier DONE
        int index = chercher(idLivraison);
        if (index!= -1){
            return listeLivraison.get(index);
        }
        return null;
    }

    /**
     * Supprime toutes les livraisons en cours pour ce livreur.
     *
     */
    public void vider() {
        // done : À compléter/modifier
        listeLivraison.clear();
    }

    /**
     * Teste si la liste est vide.
     *
     * @return true si la liste est vide, false sinon.
     */
    public boolean estVide() {
        // done : À compléter/modifier
        return listeLivraison.isEmpty();
    }

    /**
     * Retourne le nombre de livraisons présentes dans cette liste.
     *
     * @return Le nombre de livraisons.
     */
    public int taille() {
        // done : À compléter/modifier
        return listeLivraison.size();
    }
    /**
     * Affiche les éléments contenus dans la file.
     *
     */
    public void afficher() {
        // DODO : À compléter/modifier
        for (Livraison livraison : this.listeLivraison) {
            System.out.println(livraison);
        }
    }
    /**
     * Retourne un itérateur pour cette liste.
     *
     * @return Un itérateur pour cette liste.
     */
    @Override
    public ListIterator<Livraison> iterator() {
        // done : À compléter/modifier
        return listeLivraison.listIterator();
    }

    /**
     * Méthode privée pour chercher l'indice d'une livraison.
     *
     * @param idLivraison L'id de la livraison à chercher.
     * @return L'indice de la livraison trouvée ou -1 si non trouvée.
     */
    private int chercher(int idLivraison) {
        // done : À compléter/modifier
        int index = -1;
        for (int i=0; i < listeLivraison.size(); i++){
            if (listeLivraison.get(i).getId() == idLivraison){
                index = i;
                return index;
            }
        }
        return index;
    }
}
