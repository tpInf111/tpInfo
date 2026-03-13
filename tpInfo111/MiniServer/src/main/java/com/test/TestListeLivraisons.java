package com.test;

import com.gestionnaireLivraisons.*;

public class TestListeLivraisons {
    /**
     * Test de la méthode ajouter.
     */
    private static ListeLivraisons listeLivraisons = new ListeLivraisons();
    private static Livraison lv1= (new Livraison(Priorite.NORMALE, 3));
    private static Livraison lv2= (new Livraison(Priorite.URGENTE, 3));
    private static Livraison lv3= (new Livraison(Priorite.NORMALE, 2));

    private static void testAjouter() {
        System.out.print("Test ajouter : "+"\n");

        // DON : À compléter/modifier
        listeLivraisons.ajouter(lv1);
        listeLivraisons.ajouter(lv2);
        listeLivraisons.ajouter(lv3);
        listeLivraisons.afficher();
    }

    /**
     * Test de la méthode supprimer.
     */
    private static void testSupprimer() {
        System.out.print("Test supprimer : "+"\n");

        // DON : À compléter/modifier
        listeLivraisons.supprimer(2);
        listeLivraisons.afficher();
    }

    /**
     * Test de la méthode rechercher.
     */
    private static void testRechercher() {
        System.out.print("Test rechercher : "+"\n");

        // TODO : À compléter/modifier

       // System.out.println("Recherce de ID 2 (il a etait suprimer)"+listeLivraisons.rechercher(2));
        System.out.println("Recherce de ID 1 (il a etait suprimer)"+listeLivraisons.rechercher(1));
        System.out.println("Recherce de ID 3 (il a etait suprimer)"+listeLivraisons.rechercher(3));


    }

    /**
     * Test de la méthode vider.
     */
    private static void testVider() {
        System.out.print("Test vider : "+"\n");

        // TODO : À compléter/modifier
        listeLivraisons.vider();
        System.out.println("Le contnuer de la liste est ");
        listeLivraisons.afficher();
    }

    /**
     * Test de la méthode estVide.
     */
    private static void testEstVide() {
        System.out.print("Test estVide : "+"\n");

        // TODO : À compléter/modifier
        System.out.println(" la liste est vide: "+ listeLivraisons.estVide());
    }

    /**
     * Test de la méthode taille.
     */
    private static void testTaille() {
        System.out.print("Test taille : "+"\n");

        // TODO : À compléter/modifier
        System.out.println("taille avans ajout: "+ listeLivraisons.taille());
        listeLivraisons.ajouter(lv1);
        listeLivraisons.ajouter(lv2);
        System.out.println(listeLivraisons.taille());
    }


    /**
     * Lancements des tests pour la classe ListeChaineeLivreurs.
     */
    public static void tests() {
        System.out.println("----- TEST ListeLivraisons -----");

        TestListeLivraisons.testAjouter();
        TestListeLivraisons.testSupprimer();
        TestListeLivraisons.testRechercher();
        TestListeLivraisons.testVider();
        TestListeLivraisons.testEstVide();
        TestListeLivraisons.testTaille();
    }
}
