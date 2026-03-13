package com.test;

import com.gestionnaireLivraisons.*;

public class TestListeChaineeLivreurs {

    private static ListeChaineeLivreurs chaineTest = new ListeChaineeLivreurs();
    /**
     * Test de la méthode ajouter.
     */
    static Livreur lv2 = new LivreurCamion (2,"Annabelle");
    static Livreur lv3 = new LivreurVelo (3,"Pomme");
    static Livreur lv4 = new LivreurVoiture (4,"Martine");
    private static void testAjouter() throws ListeChaineeException {
        System.out.print("Test ajouter : "+"\n");

        // DODO : À compléter/modifier
        Livreur lv1 = new LivreurCamion (1,"Mealle");
        chaineTest.ajouter(lv2);
        chaineTest.ajouter(lv3);
        chaineTest.ajouter(lv4);
        chaineTest.affiche();
    }

    /**
     * Test de la méthode supprimer.
     */
    private static void testSupprimer() {
        System.out.print("Test supprimer : "+"\n");

        // DODO : À compléter/modifier
        chaineTest.supprimer(4);
        chaineTest.affiche();
    }

    /**
     * Test de la méthode rechercher.
     */
    private static void testRechercher() {
        System.out.print("Test rechercher : "+"\n");

        // DODO : À compléter/modifier
        System.out.println(chaineTest.rechercher(3));
    }

    /**
     * Test de la méthode taille.
     */
    private static void testTaille() {
        System.out.print("Test taille : "+"\n");

        // DODO : À compléter/modifier
        System.out.println(chaineTest.taille());
    }

    /**
     * Test de la méthode toArray.
     */
    private static void testToArray() {
        System.out.print("Test toArray : "+"\n");

        // DODO : À compléter/modifier
        System.out.println(chaineTest.toArray());
    }

    /**
     * Lancements des tests pour la classe ListeChaineeLivreurs.
     */
    public static void tests() throws ListeChaineeException {
        System.out.println("----- TEST ListeChaineeLivreurs -----");

        TestListeChaineeLivreurs.testTaille();
        TestListeChaineeLivreurs.testAjouter();
        TestListeChaineeLivreurs.testSupprimer();
        TestListeChaineeLivreurs.testRechercher();
        TestListeChaineeLivreurs.testToArray();
    }
}
